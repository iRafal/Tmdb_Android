package com.tmdb.di.module

import com.tmdb.ui.core.di.module.ImageLoadingModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [ImageLoadingModule::class])
interface UiModule

