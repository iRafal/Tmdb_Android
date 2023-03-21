package com.tmdb.api.config.url.provider.base.di

import com.tmdb.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.api.config.url.provider.base.BaseUrlProviderImpl
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