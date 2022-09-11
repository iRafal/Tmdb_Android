package com.tmdb_test.di.data.api.util

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