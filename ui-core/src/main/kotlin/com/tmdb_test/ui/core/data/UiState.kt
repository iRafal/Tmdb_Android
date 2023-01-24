package com.tmdb_test.ui.core.data


sealed interface UiState<T> {
    class Loading<T> : UiState<T> {
        override fun equals(other: Any?): Boolean = when {
            this === other -> true
            javaClass != other?.javaClass -> false
            else -> true
        }

        override fun hashCode(): Int = javaClass.hashCode()
    }

    data class Error<T>(val cause: Throwable? = null) : UiState<T>
    data class NetworkError<T>(val cause: Throwable? = null) : UiState<T>
    data class Success<T>(val data: T) : UiState<T>
}