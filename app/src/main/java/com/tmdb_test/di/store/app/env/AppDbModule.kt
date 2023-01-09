package com.tmdb_test.di.store.app.env

import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.env.createAppDbEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AppDbModule {

    @Provides
    fun appDatabase(): AppEnv.Database = createAppDbEnvImpl()
}