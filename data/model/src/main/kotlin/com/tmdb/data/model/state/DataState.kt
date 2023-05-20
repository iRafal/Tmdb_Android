package com.tmdb.data.model.state

sealed interface DataState<T> {
    data class Error<T>(val cause: Throwable? = null) : DataState<T>
    data class NetworkError<T>(val cause: Throwable? = null) : DataState<T>
    data class Success<T>(val data: T) : DataState<T>

    val isSuccess: Boolean
        get() = this is Success<T>

    val isNetworkError: Boolean
        get() = this is NetworkError<T>

    val isError: Boolean
        get() = this is Error<T>
}
