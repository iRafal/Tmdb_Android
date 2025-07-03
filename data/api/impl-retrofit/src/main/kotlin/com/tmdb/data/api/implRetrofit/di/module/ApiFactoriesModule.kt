package com.tmdb.data.api.implRetrofit.di.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tmdb.data.api.implRetrofit.util.NetworkResponseAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.scalars.ScalarsConverterFactory

fun apiFactoriesModule() = module {
    factory<Converter.Factory>(named("ConverterFactoryScalars")) { ScalarsConverterFactory.create() }
    factory<Converter.Factory>(named("ConverterFactoryJson")) {
        val contentType = "application/json".toMediaType()
        // https://petnagy.medium.com/kotlinx-serialization-part2-d6c23f7839c4
        get<Json>().asConverterFactory(contentType)
    }
    factory<CallAdapter.Factory> { NetworkResponseAdapterFactory() }
}
