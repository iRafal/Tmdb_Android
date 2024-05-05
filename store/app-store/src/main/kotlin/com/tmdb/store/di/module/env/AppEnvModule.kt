package com.tmdb.store.di.module.env

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppEnvImpl
import dagger.Module
import dagger.Provides


@Module(includes = [AppDbModule::class, AppNetworkModule::class])
object AppEnvModule {

    @Provides
    fun appEnv(appNetwork: AppEnv.Network, appDatabase: AppEnv.Database): AppEnv =
        createAppEnvImpl(appNetwork, appDatabase)
}
