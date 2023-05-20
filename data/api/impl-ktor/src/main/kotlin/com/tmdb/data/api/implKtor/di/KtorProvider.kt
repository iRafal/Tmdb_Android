package com.tmdb.data.api.implKtor.di

import com.tmdb.api.model.util.ApiException
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType.Application
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json
import logcat.LogPriority.DEBUG
import logcat.logcat

private const val TIMEOUT_DEFAULT = 10_000
private const val PARAMETER_API_KEY = "api_key"

fun createKtorHttpClient(
    apiKey: String,
    logLevel: LogLevel,
    apiErrorMapper: ApiErrorMapper,
    logger: Logger,
    json: Json,
    connectTimeout: Int = TIMEOUT_DEFAULT
): HttpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json(json)
    }

    engine {
        this.connectTimeout = connectTimeout
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, Application.Json)
    }

    install(Logging) {
        this.logger = logger
        level = logLevel
    }

    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, request ->
            throw apiErrorMapper(cause)
        }
    }
}.also {
    it.plugin(HttpSend).intercept { request ->
        request.url.parameters.append(PARAMETER_API_KEY, apiKey)
        execute(request)
    }
}

typealias ApiErrorMapper = suspend (cause: Throwable) -> ApiException

val apiErrorMapper: ApiErrorMapper = { cause ->
    when (cause) {
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

fun ktorLogger(): Logger = object : Logger {
    override fun log(message: String) {
        logcat(DEBUG) { "Ktor Api Log: $message" }
    }
}
