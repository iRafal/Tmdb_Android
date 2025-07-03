package com.tmdb.di.module

import com.tmdb.ui.core.di.module.ImageLoadingModule
import com.tmdb.ui.core.di.module.imageLoadingModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [ImageLoadingModule::class])
interface UiModule

fun uiModule() = module {
    imageLoadingModule()
}
