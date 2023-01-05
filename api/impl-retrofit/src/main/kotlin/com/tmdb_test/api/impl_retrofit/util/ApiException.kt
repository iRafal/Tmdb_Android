package com.tmdb_test.api.impl_retrofit.util

sealed class ApiException(
    override val cause: Throwable?,
    override val message: String? = null
) : Throwable(message, cause) {

    class NetworkError(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    class InternalServerError(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    class Unauthorized(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    class BadRequest(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)

    class HttpError(
        override val cause: Throwable? = null,
        override val message: String? = null,
        val code: Int
    ) : ApiException(cause, message)

    class UnknownError(
        override val cause: Throwable? = null,
        override val message: String? = null
    ) : ApiException(cause, message)
}