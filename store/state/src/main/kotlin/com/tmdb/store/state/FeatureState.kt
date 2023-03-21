package com.tmdb.store.state


sealed interface FeatureState<T> {
    class Loading<T> : FeatureState<T> {
        override fun equals(other: Any?): Boolean = when {
            this === other -> true
            javaClass != other?.javaClass -> false
            else -> true
        }

        override fun hashCode(): Int = javaClass.hashCode()
    }

    data class Error<T>(val cause: Throwable? = null) : FeatureState<T>
    data class NetworkError<T>(val cause: Throwable? = null) : FeatureState<T>
    data class Success<T>(val data: T) : FeatureState<T>

    val isLoading
        get() = this is Loading

    val isError
        get() = this is Error

    val isNetworkError
        get() = this is NetworkError

    val isSuccess
        get() = this is Success
}