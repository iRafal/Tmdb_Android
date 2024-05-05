package com.tmdb.ui.core.util.viewModel.assisted

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

/**
 * Interface for constructing [ViewModel] that gets an instance of [SavedStateHandle]
 * as a parameter.
 *
 * Implement this interface passing all [ViewModel] dependencies to the implementation's
 * injectable constructor and return a new instance of the [ViewModel].
 *
 * Inject an instance of the implementation and pass it
 * to [withFactory] when using `by viewModels` like so:
 *
 *
 * ```
 * private val viewModel: CommitListViewModelNew by viewModels {
 *     withFactory(commitListViewModelCreator, this)
 * }
 * ```
 *
 * @see withFactory
 */
interface AssistedViewModelCreator<out T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}

/**
 * Convenience function to use with `by viewModels` that creates an instance of
 * [AbstractSavedStateViewModelFactory] that enables us to pass [SavedStateHandle]
 * to the [ViewModel]'s constructor.
 *
 * @param creator instance of [AssistedViewModelCreator] that will be used to construct the [ViewModel]
 * @param defaultArgs Bundle with default values to populate the [SavedStateHandle]
 *
 * @see AssistedViewModelCreator
 */
@MainThread
inline fun <reified T : ViewModel> SavedStateRegistryOwner.withFactory(
    creator: AssistedViewModelCreator<T>,
    defaultArgs: Bundle? = null
) = AssistedViewModelFactory(creator, this, defaultArgs)
