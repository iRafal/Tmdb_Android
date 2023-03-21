package com.tmdb.data.local.impl_object_box.di

import com.tmdb.data.local.impl_object_box.MovieLocalDataSourceImpl
import com.tmdb.data.source.remote.contract.MovieLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {
    @Binds
    fun movieLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource
}