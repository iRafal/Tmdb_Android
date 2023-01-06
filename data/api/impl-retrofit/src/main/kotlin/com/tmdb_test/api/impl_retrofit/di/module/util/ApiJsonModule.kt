package com.tmdb_test.api.impl_retrofit.di.module.util

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class ApiJsonModule {

    @Provides
    fun json() = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}