package com.tmdb_test.di.data.api.util

import com.tmdb_test.BuildConfig
import com.tmdb_test.data.api.impl_retrofit.util.ApiResponseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY


@Module
@InstallIn(SingletonComponent::class)
class ApiHttpClientModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorResponse

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorLogging

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorRequest

    @Provides
    @InterceptorResponse
    fun apiResponseInterceptor(): Interceptor = ApiResponseInterceptor()

    @Provides
    @InterceptorLogging
    fun loggingInterceptor(): Interceptor = loggingInterceptor(BODY)

    private fun loggingInterceptor(
        loggingLevel: Level,
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = loggingLevel }
    }

    @Provides
    @InterceptorRequest
    fun requestInterceptor(): Interceptor {
        return requestInterceptor(apiKeyKey = "api_key", apiKeyValue = BuildConfig.API_KEY)
    }

    private fun requestInterceptor(apiKeyKey: String, apiKeyValue: String): Interceptor {
        return Interceptor { chain ->
            val original: Request = chain.request()

            val url = original.url.newBuilder()
                .addQueryParameter(apiKeyKey, apiKeyValue)
                .build()

            val request: Request = original.newBuilder().url(url).build()

            chain.proceed(request)
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttpClientRetrofit

    @Provides
    @OkHttpClientRetrofit
    fun okHttpClient(
        @InterceptorLogging loggingInterceptor: Interceptor,
        @InterceptorRequest requestInterceptor: Interceptor,
        @InterceptorResponse apiResponseInterceptor: Interceptor,
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