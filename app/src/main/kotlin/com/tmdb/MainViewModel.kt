package com.tmdb

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val internalIsLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = internalIsLoading.asStateFlow()

    fun setLoading() {
        internalIsLoading.value = true
    }

    fun setNotLoading() {
        internalIsLoading.value = false
    }
}
