package com.tmdb.data.api.config.di.module

import dagger.Module

@Module(includes = [ImageUrlModule::class, UrlProviderModule::class])
object ApiConfigModule
