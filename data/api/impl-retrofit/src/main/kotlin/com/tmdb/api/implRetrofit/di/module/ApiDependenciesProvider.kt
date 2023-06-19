package com.tmdb.api.implRetrofit.di.module

import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

object ApiDependenciesProvider {

    const val API_KEY = "api_key"

    fun <T> api(
        url: String,
        client: OkHttpClient,
        jsonConverterFactory: Converter.Factory,
        scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory,
        apiClass: Class<T>
    ): T = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(scalarsConverterFactory)
        .addCallAdapterFactory(factory)
        .build()
        .create(apiClass)

    fun requestInterceptor(apiKey: String, apiKeyValue: String): Interceptor {
        return Interceptor { chain ->
            val original: Request = chain.request()

            val url = original.url.newBuilder()
                .addQueryParameter(apiKey, apiKeyValue)
                .build()

            val request: Request = original.newBuilder().url(url).build()

            chain.proceed(request)
        }
    }

    fun json(): Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}