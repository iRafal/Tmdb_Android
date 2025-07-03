package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun apiJsonModule() = module {
    factory<Json> { ApiDependenciesProvider.json() }
}
