package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ImageUrlModule {

    @Provides
    fun imageUrlProvider(baseUrlProvider: BaseUrlProvider): ImageUrlProvider =
        ImageUrlProviderImpl(baseUrlProvider.apiImageUrl)
}
