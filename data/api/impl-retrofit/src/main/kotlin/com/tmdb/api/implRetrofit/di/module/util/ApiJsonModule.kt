package com.tmdb.api.implRetrofit.di.module.util

import com.tmdb.api.implRetrofit.di.module.ApiDependenciesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
object ApiJsonModule {

    @Provides
    fun json() = ApiDependenciesProvider.json()
}
