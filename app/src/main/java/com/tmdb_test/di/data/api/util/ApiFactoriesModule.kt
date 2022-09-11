package com.tmdb_test.di.data.api.util

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tmdb_test.data.api.impl_retrofit.util.NetworkResponseAdapterFactory
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


@Module
@InstallIn(SingletonComponent::class)
class ApiFactoriesModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ConverterFactoryScalars

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ConverterFactoryJson

    @Provides
    @ConverterFactoryScalars
    fun scalarsConverterFactory(): Converter.Factory = ScalarsConverterFactory.create()

    @Provides
    @ConverterFactoryJson
    @ExperimentalSerializationApi
    fun jsonConverterFactory(json: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        // https://petnagy.medium.com/kotlinx-serialization-part2-d6c23f7839c4
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun networkResponseFactory(): CallAdapter.Factory = NetworkResponseAdapterFactory()
}