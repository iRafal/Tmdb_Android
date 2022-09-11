package com.tmdb_test.di.data.api.url.provider.image

import com.tmdb_test.BuildConfig
import com.tmdb_test.data.api.url.provider.image.ImageUrlProvider
import com.tmdb_test.data.api.url.provider.image.ImageUrlProviderImpl
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