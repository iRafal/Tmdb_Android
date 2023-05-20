package com.tmdb.api.config.url.image.di

import com.tmdb.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.api.config.url.provider.base.BaseUrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageUrlModule {

    @Provides
    @Singleton
    fun imageUrlProvider(baseUrlProvider: BaseUrlProvider): ImageUrlProvider =
        ImageUrlProviderImpl(baseUrlProvider.apiImageUrl)
}
