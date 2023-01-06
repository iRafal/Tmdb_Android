package com.tmdb_test.api.config.di.module.image

import com.tmdb_test.api.config.BuildConfig
import com.tmdb_test.api.config.url.image.ImageUrlProvider
import com.tmdb_test.api.config.url.image.ImageUrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ImageUrlModule {

    @Provides
    fun imageUrlProvider(): ImageUrlProvider = ImageUrlProviderImpl(BuildConfig.API_IMAGE_URL)
}