package com.tmdb.api.implRetrofit.di.module.util

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object ApiJsonModule {

    @Provides
    fun json() = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}
