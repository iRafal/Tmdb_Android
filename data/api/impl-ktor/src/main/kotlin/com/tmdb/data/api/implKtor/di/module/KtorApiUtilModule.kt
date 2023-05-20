package com.tmdb.data.api.implKtor.di.module

import com.tmdb.api.config.BuildConfig
import com.tmdb.data.api.implKtor.di.ApiErrorMapper
import com.tmdb.data.api.implKtor.di.apiErrorMapper
import com.tmdb.data.api.implKtor.di.createKtorHttpClient
import com.tmdb.data.api.implKtor.di.ktorLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel.BODY
import io.ktor.client.plugins.logging.Logger
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object KtorApiUtilModule {

    @Provides
    @Singleton
    fun getKtorHttpClient(
        json: Json,
        logger: Logger,
        apiErrorMapper: @JvmSuppressWildcards ApiErrorMapper
    ): HttpClient = createKtorHttpClient(
        apiKey = BuildConfig.API_KEY,
        logLevel = BODY,
        apiErrorMapper = apiErrorMapper,
        logger = logger,
        json = json
    )

    @Provides
    fun ktorApiLogger(): Logger = ktorLogger()

    @Provides
    fun apiErrorMapper(): @JvmSuppressWildcards ApiErrorMapper = apiErrorMapper

    @Provides
    fun json() = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}
