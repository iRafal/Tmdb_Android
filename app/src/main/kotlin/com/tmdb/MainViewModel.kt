package com.tmdb

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val internalIsLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = internalIsLoading.asStateFlow()

    fun setLoading() {
        internalIsLoading.value = true
    }

    fun setNotLoading() {
        internalIsLoading.value = false
    }
}
