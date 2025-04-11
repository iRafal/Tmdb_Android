package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ApiJsonModule {

    @Provides
    fun json() = ApiDependenciesProvider.json()
}
