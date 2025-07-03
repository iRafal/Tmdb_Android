package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.api.config.BuildConfig
import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import com.tmdb.data.api.implRetrofit.util.ApiResponseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Qualifier
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.core.qualifier.named
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
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
        return ApiDependenciesProvider.requestInterceptor(
            apiKey = ApiDependenciesProvider.API_KEY,
            apiKeyValue = BuildConfig.API_KEY
        )
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
