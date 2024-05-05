package com.tmdb.api.model.util

sealed interface ApiResponse<out T : Any, out U : Any> {
    data class Success<T : Any>(val data: T) : ApiResponse<T, Nothing>

    data class ApiError<U : Any>(
        val body: U? = null,
        val code: Int? = null,
        val cause: ApiException? = null
    ) : ApiResponse<Nothing, U>

    data class NetworkError(
        val cause: ApiException.NetworkError? = null
    ) : ApiResponse<Nothing, Nothing>

    data class UnknownError(val cause: Throwable? = null) : ApiResponse<Nothing, Nothing>

    val isUnknownError: Boolean
        get() = this is UnknownError

    val isApiError: Boolean
        get() = this is ApiError

    val isNetworkError: Boolean
        get() = this is NetworkError

    val isSuccess: Boolean
        get() = this is Success<T>
}
