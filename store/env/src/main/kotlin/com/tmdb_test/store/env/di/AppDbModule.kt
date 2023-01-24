package com.tmdb_test.store.env.di

import com.tmdb_test.data.source.remote.contract.MovieLocalDataSource
import com.tmdb_test.store.env.contract.AppEnv
import com.tmdb_test.store.env.impl.createAppDbEnvImpl
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