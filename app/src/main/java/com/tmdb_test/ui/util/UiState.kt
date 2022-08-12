package com.tmdb_test.ui.util


sealed interface UiState<T> {
    class Loading<T> : UiState<T>
    class Error<T>(val cause: Throwable? = null) : UiState<T>
    class NetworkError<T>(val cause: Throwable? = null) : UiState<T>
    class Success<T>(val data: T) : UiState<T>
}