package com.tmdb_test.data.util


sealed interface DataState<T> {
    class Error<T>(val cause: Throwable? = null) : DataState<T>
    class NetworkError<T>(val cause: Throwable? = null) : DataState<T>
    class Success<T>(val data: T) : DataState<T>
}