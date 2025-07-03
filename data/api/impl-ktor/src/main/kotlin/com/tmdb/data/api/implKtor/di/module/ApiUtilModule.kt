package com.tmdb.data.api.implKtor.di.module

import com.tmdb.api.config.BuildConfig
import com.tmdb.data.api.implKtor.di.createKtorHttpClient
import com.tmdb.data.api.implKtor.util.ApiErrorMapper
import com.tmdb.data.api.implKtor.util.ApiErrorMapperImpl
import com.tmdb.data.api.implKtor.util.KtorLogger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel.BODY
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun apiUtilModule() = module {
    factory<HttpClient> {
        createKtorHttpClient(
            apiKey = BuildConfig.API_KEY,
            logLevel = BODY,
            apiErrorMapper = get<ApiErrorMapper>(),
            logger = get<Logger>(),
            json = get<Json>()
        )
    }
    factory<Logger> { KtorLogger() }
    factory<ApiErrorMapper> { ApiErrorMapperImpl() }
    factory<Json> {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }
}
