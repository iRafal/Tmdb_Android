package com.tmdb_test.data.api.impl_ktor.di.module

import com.tmdb_test.api.config.BuildConfig
import com.tmdb_test.data.api.impl_ktor.di.ApiErrorMapper
import com.tmdb_test.data.api.impl_ktor.di.apiErrorMapper
import com.tmdb_test.data.api.impl_ktor.di.createKtorHttpClient
import com.tmdb_test.data.api.impl_ktor.di.ktorLogger
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
    @JvmStatic
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
        json = json,
    )

    @Provides
    @JvmStatic
    fun ktorApiLogger(): Logger = ktorLogger()

    @Provides
    @JvmStatic
    fun apiErrorMapper(): @JvmSuppressWildcards ApiErrorMapper = apiErrorMapper

    @Provides
    @JvmStatic
    fun json() = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}