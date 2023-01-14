package com.tmdb_test.api.model.util

sealed class ApiException(
    override val cause: Throwable?,
    override val message: String? = null
) : Throwable(message, cause) {

    data class NetworkError(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    data class InternalServerError(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    data class Unauthorized(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    data class BadRequest(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    data class HttpError(
        override val cause: Throwable? = null,
        override val message: String? = null,
        val code: Int
    ) : ApiException(cause, message)

    data class UnknownError(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)
}