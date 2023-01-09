package com.tmdb_test.store.state


sealed interface FeatureState<T> {
    class Loading<T> : FeatureState<T>
    class Error<T>(val cause: Throwable? = null) : FeatureState<T>
    class NetworkError<T>(val cause: Throwable? = null) : FeatureState<T>
    class Success<T>(val data: T) : FeatureState<T>

    val isLoading
        get() = this is Loading

    val isError
        get() = this is Error

    val isNetworkError
        get() = this is NetworkError

    val isSuccess
        get() = this is Success
}