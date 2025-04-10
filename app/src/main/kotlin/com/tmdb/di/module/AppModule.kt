package com.tmdb.di.module

import com.tmdb.store.di.module.app.AppStoreModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        UiModule::class, AppStoreModule::class
    ]
)
internal object AppModule