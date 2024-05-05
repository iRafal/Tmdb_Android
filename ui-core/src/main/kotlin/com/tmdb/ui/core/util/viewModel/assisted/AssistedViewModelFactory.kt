@file:Suppress("MaxLineLength")
package com.tmdb.ui.core.util.viewModel.assisted

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner


/**
 * @see <a href="https://medium.com/wriketechclub/how-to-inject-viewmodel-with-dagger-what-might-go-wrong-7954372a7fb9">Inspired by</a>
 */
class AssistedViewModelFactory<out V : ViewModel>(
    private val assistedViewModelCreator: AssistedViewModelCreator<V>,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return assistedViewModelCreator.create(handle) as? T
            ?: throw IllegalArgumentException("Can't cast provided ViewModel to type ${modelClass::getSimpleName}")
    }
}
