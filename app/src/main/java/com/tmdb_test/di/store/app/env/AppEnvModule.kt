package com.tmdb_test.di.store.app.env

import com.tmdb_test.store.env.AppEnv
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppEnvModule {

    @Provides
    fun appEnv(appNetwork: AppEnv.Network, appDatabase: AppEnv.Database): AppEnv {
        return object : AppEnv {
            override val network: AppEnv.Network = appNetwork
            override val database: AppEnv.Database = appDatabase
        }
    }
}