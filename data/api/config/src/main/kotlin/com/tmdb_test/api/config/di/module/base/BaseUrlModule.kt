package com.tmdb_test.api.config.di.module.base

import com.tmdb_test.api.config.url.provider.BaseUrlProvider
import com.tmdb_test.api.config.url.provider.BaseUrlProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface BaseUrlModule {
    @Binds
    fun baseUrlProvider(impl: BaseUrlProviderImpl): BaseUrlProvider
}