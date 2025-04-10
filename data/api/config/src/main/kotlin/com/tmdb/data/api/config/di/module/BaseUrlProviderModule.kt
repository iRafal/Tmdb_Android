package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
object BaseUrlProviderModule {
    @Provides
    fun baseUrlProvider(impl: BaseUrlProviderImpl): BaseUrlProvider = impl
}
