package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [ImageUrlModule::class, UrlProviderModule::class])
object ApiConfigModule

fun apiConfigModule() = module {
    imageUrlModule()
    urlProviderModule()
}
