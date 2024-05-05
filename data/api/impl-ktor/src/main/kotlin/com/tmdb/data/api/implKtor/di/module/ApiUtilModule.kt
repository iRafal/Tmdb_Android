package com.tmdb.data.api.implKtor.di.module

import com.tmdb.api.config.BuildConfig
import com.tmdb.data.api.implKtor.di.createKtorHttpClient
import com.tmdb.data.api.implKtor.util.ApiErrorMapper
import com.tmdb.data.api.implKtor.util.ApiErrorMapperImpl
import com.tmdb.data.api.implKtor.util.KtorLogger
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel.BODY
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json


@Module
object ApiUtilModule {

    @Provides
    fun getKtorHttpClient(
        json: Json,
        logger: Logger,
        apiErrorMapper: ApiErrorMapper
    ): HttpClient = createKtorHttpClient(
        apiKey = BuildConfig.API_KEY,
        logLevel = BODY,
        apiErrorMapper = apiErrorMapper,
        logger = logger,
        json = json
    )

    @Provides
    fun ktorApiLogger(impl: KtorLogger): Logger = impl

    @Provides
    fun apiErrorMapper(impl: ApiErrorMapperImpl): ApiErrorMapper = impl

    @Provides
    fun json() = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}
