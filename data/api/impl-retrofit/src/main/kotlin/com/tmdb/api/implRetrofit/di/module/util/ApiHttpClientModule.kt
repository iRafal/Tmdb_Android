package com.tmdb.api.implRetrofit.di.module.util

import com.tmdb.api.config.BuildConfig
import com.tmdb.api.implRetrofit.di.module.ApiDependenciesProvider
import com.tmdb.api.implRetrofit.util.ApiResponseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY


@[Module InstallIn(SingletonComponent::class)]
object ApiHttpClientModule {

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class InterceptorResponse

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class InterceptorLogging

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class InterceptorRequest

    @[InterceptorResponse Provides]
    fun apiResponseInterceptor(): Interceptor = ApiResponseInterceptor()

    @[InterceptorLogging Provides]
    fun loggingInterceptor(): Interceptor = loggingInterceptor(BODY)

    private fun loggingInterceptor(
        loggingLevel: Level
    ): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = loggingLevel }

    @[InterceptorRequest Provides]
    fun requestInterceptor(): Interceptor {
        return ApiDependenciesProvider.requestInterceptor(apiKey = ApiDependenciesProvider.API_KEY, apiKeyValue = BuildConfig.API_KEY)
    }

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class OkHttpClientRetrofit

    @[OkHttpClientRetrofit Provides]
    fun okHttpClient(
        @InterceptorLogging loggingInterceptor: Interceptor,
        @InterceptorRequest requestInterceptor: Interceptor,
        @InterceptorResponse apiResponseInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(requestInterceptor)
            .addInterceptor(apiResponseInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }
}
