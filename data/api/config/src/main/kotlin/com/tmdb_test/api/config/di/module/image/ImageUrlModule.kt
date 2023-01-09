package com.tmdb_test.api.config.di.module.image

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb_test.api.config.url.provider.BaseUrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ImageUrlModule {

    @Provides
    fun imageUrlProvider(baseUrlProvider: BaseUrlProvider): ImageUrlProvider =
        ImageUrlProviderImpl(baseUrlProvider.apiImageUrl)
}