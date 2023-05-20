package com.tmdb.data.source.local.impl.realm.di

import com.tmdb.data.source.local.impl.realm.MovieLocalDataSourceImpl
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
