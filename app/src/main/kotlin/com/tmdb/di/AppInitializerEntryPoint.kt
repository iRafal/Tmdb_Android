package com.tmdb.di

import coil3.ImageLoader
import com.tmdb.ui.core.di.module.ImageLoadingModule
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppInitializerEntryPoint {
    @ImageLoadingModule.CoilOkHttpImageLoader
    fun coilImageLoader(): ImageLoader
}
