package com.tmdb.ui.core.util.viewModel

import androidx.lifecycle.ViewModel

interface ViewModelCreator<out T: ViewModel> {
    fun create(): T
}
