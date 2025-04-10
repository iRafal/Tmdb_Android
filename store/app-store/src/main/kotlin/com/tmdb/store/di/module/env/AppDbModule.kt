package com.tmdb.store.di.module.env

import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppDbModule {

    @Provides
    fun appDatabase(movieSource: MovieLocalDataSource): AppEnv.Database = createAppDbEnvImpl(movieSource)
}
