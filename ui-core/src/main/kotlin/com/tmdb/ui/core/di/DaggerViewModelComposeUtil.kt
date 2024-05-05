package com.tmdb.ui.core.di

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tmdb.ui.core.util.viewModel.assisted.AssistedViewModelCreator
import com.tmdb.ui.core.util.viewModel.assisted.withFactory

/**
 * https://proandroiddev.com/dagger-2-and-jetpack-compose-integration-8a8d424ffdb4
 */
@Suppress("UNCHECKED_CAST")
@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    key: String? = null,
    crossinline viewModelInstanceCreator: () -> T
): T = viewModel(
    modelClass = T::class.java,
    key = key,
    factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModelInstanceCreator() as? T
                ?: throw IllegalArgumentException("Can't cast provided ViewModel to type ${modelClass::getSimpleName}")
        }
    }
)

/**
 * Usage:
 * - Create ViewModel, e.g. HomeViewModel (don't inject it constructor)
 * - Create nested class Creator in ViewModel, e.g. HomeViewModel.Creator
 * - Extend AssistedViewModelCreator by e.g. HomeViewModel.Creator
 * - Inject `Creator` constructor
 * - Add ViewModel.Creator to Component as dependency which it can provide
 * - Create screen, inject `HomeViewModel.Creator` into screen or get it directly from component
 * - Pass a creator as input for [daggerAssistedViewModel]
 *
 * ```
 *      // Dagger component interface
 *      interface Component {
 *         val homeViewModelCreator: HomeViewModel.Creator
 *      }
 *
 *      // ViewModel implementation
 *      class HomeViewModel(private val handle: SavedStateHandle, private val store: AppStore) : ViewModel() {
 *          val id: String
 *              get() = checkNotNull(handle["KEY"])
 *
 *          class Creator @Inject constructor(private val store: AppStore) : AssistedViewModelCreator<HomeViewModel> {
 *              override fun create(handle: SavedStateHandle): HomeViewModel = HomeViewModel(handle, store)
 *          }
 *      }
 *
 *     // Usage in compose
 *     val component = remember { HomeFeatureDi.fromContext(context) }
 *     val viewModel: HomeViewModel = daggerAssistedViewModel(assistedViewModelCreator = component.homeViewModelCreator)
 *
 *     // Usage in Activity, ViewFragment is without [daggerAssistedViewModel], please use [withFactory]
 *     @Inject
 *     val homeViewModelCreator: HomeViewModel.Creator
 *     ...
 *     HomeFeatureDi.fromContext(context).inject(this)
 *     ...
 *     context.withFactory(homeViewModelCreator)
 *
 * ```
 */
@Composable
inline fun <reified T : ViewModel> daggerAssistedViewModel(
    key: String? = null,
    assistedViewModelCreator: AssistedViewModelCreator<T>,
    defaultArgs: Bundle? = null
): T = viewModel(
    modelClass = T::class.java,
    key = key,
    factory = LocalSavedStateRegistryOwner.current.withFactory(assistedViewModelCreator, defaultArgs = defaultArgs)
)
