package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
object ApiJsonModule {

    @Provides
    fun json() = ApiDependenciesProvider.json()
}

fun apiJsonModule() = module {
    factory<Json> { ApiDependenciesProvider.json() }
}
