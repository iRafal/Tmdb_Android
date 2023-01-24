package com.tmdb_test.api.config.url.provider.base.di

import com.tmdb_test.api.config.url.provider.base.BaseUrlProvider
import com.tmdb_test.api.config.url.provider.base.BaseUrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BaseUrlProviderModule {
    @Provides
    @Singleton
    fun baseUrlProvider(impl: BaseUrlProviderImpl): BaseUrlProvider = impl
}