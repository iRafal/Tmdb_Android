package com.tmdb.store.env.di

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
object AppEnvModule {

    @Provides
    fun appEnv(appNetwork: AppEnv.Network, appDatabase: AppEnv.Database): AppEnv =
        createAppEnvImpl(appNetwork, appDatabase)
}
