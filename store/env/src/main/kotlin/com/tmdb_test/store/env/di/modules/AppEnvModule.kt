package com.tmdb_test.store.env.di.modules

import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.env.createAppEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppEnvModule {

    @Provides
    fun appEnv(appNetwork: AppEnv.Network, appDatabase: AppEnv.Database): AppEnv =
        createAppEnvImpl(appNetwork, appDatabase)
}