package com.tmdb.store.env.di

import com.tmdb.data.source.remote.contract.MovieLocalDataSource
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppDbModule {

    @Provides
    fun appDatabase(movieSource: MovieLocalDataSource): AppEnv.Database =
        createAppDbEnvImpl(movieSource)
}