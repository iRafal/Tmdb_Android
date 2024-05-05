package com.tmdb.data.api.implKtor.di

import com.tmdb.data.api.implKtor.util.ApiErrorMapper
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.ContentType.Application
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

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
            throw apiErrorMapper.map(cause)
        }
    }
}.also {
    it.plugin(HttpSend).intercept { request ->
        request.url.parameters.append(PARAMETER_API_KEY, apiKey)
        execute(request)
    }
}
