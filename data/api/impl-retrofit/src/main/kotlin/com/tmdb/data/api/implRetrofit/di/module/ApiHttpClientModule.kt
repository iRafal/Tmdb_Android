package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.api.config.BuildConfig
import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import com.tmdb.data.api.implRetrofit.util.ApiResponseInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.core.qualifier.named
import org.koin.dsl.module

object ApiHttpClientModule {
    fun apiHttpClientModule() = module {
        factory<Interceptor>(named("InterceptorResponse")) { ApiResponseInterceptor() }
        factory<Interceptor>(named("InterceptorLogging")) { loggingInterceptor(BODY) }
        factory<Interceptor>(named("InterceptorRequest")) {
            ApiDependenciesProvider.requestInterceptor(
                apiKey = ApiDependenciesProvider.API_KEY,
                apiKeyValue = BuildConfig.API_KEY
            )
        }
        factory<OkHttpClient>(named("OkHttpClientRetrofit")) {
            OkHttpClient()
                .newBuilder()
                .addInterceptor(get<Interceptor>(named("InterceptorResponse")))
                .addInterceptor(get<Interceptor>(named("InterceptorLogging")))
                .addInterceptor(get<Interceptor>(named("InterceptorRequest")))
                .retryOnConnectionFailure(true)
                .build()
        }
    }
}

private fun loggingInterceptor(
    loggingLevel: Level
): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = loggingLevel }
