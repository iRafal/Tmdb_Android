package com.tmdb.data.api.implRetrofit.di.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tmdb.data.api.implRetrofit.util.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.scalars.ScalarsConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object ApiFactoriesModule {

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class ConverterFactoryScalars

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class ConverterFactoryJson

    @[Provides ConverterFactoryScalars]
    fun scalarsConverterFactory(): Converter.Factory = ScalarsConverterFactory.create()

    @[Provides ConverterFactoryJson ExperimentalSerializationApi]
    fun jsonConverterFactory(json: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        // https://petnagy.medium.com/kotlinx-serialization-part2-d6c23f7839c4
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun networkResponseFactory(): CallAdapter.Factory = NetworkResponseAdapterFactory()
}
