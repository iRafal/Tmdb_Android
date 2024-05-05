package com.tmdb.data.api.implKtor.util

import com.tmdb.api.model.util.ApiException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import javax.inject.Inject

fun interface ApiErrorMapper {
    suspend fun map(cause: Throwable): ApiException
}

class ApiErrorMapperImpl @Inject constructor(): ApiErrorMapper {
    override suspend fun map(cause: Throwable): ApiException {
        return when (cause) {
            is IOException -> ApiException.NetworkError(cause)
            is ClientRequestException -> {
                val requestException: ClientRequestException = cause
                val statusCode = requestException.response.status
                val responseBody = requestException.response.bodyAsText()
                when (statusCode) {
                    HttpStatusCode.InternalServerError -> {
                        ApiException.InternalServerError(requestException, responseBody)
                    }

                    HttpStatusCode.BadRequest -> {
                        ApiException.BadRequest(requestException, responseBody)
                    }

                    HttpStatusCode.Unauthorized -> {
                        ApiException.Unauthorized(requestException, responseBody)
                    }

                    else -> ApiException.HttpError(requestException, responseBody, statusCode.value)
                }
            }

            else -> ApiException.UnknownError(cause)
        }
    }
}
