package com.tmdb.data.api.config.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [ImageUrlModule::class, UrlProviderModule::class])
object ApiConfigModule
