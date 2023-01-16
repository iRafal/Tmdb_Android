package com.tmdb_test.data.model.state


sealed interface DataState<T> {
    data class Error<T>(val cause: Throwable? = null) : DataState<T>
    data class NetworkError<T>(val cause: Throwable? = null) : DataState<T>
    data class Success<T>(val data: T) : DataState<T>
}