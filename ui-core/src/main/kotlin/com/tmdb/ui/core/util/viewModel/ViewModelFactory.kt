package com.tmdb.ui.core.util.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<T : ViewModel>(private val viewModelCreator: ViewModelCreator<T>) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return viewModelCreator.create() as? T
            ?: throw IllegalArgumentException("Can't cast provided ViewModel to type ${modelClass::getSimpleName}")
    }
}
