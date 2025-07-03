package com.tmdb.store.di.module.env

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [AppDbModule::class, AppNetworkModule::class])
object AppEnvModule {

    @Provides
    fun appEnv(appNetwork: AppEnv.Network, appDatabase: AppEnv.Database): AppEnv =
        createAppEnvImpl(appNetwork, appDatabase)
}

fun appEnvModule() = module {
    appDbModule()
    appNetworkModule()
    factory<AppEnv>() {
        createAppEnvImpl(get<AppEnv.Network>(), get<AppEnv.Database>())
    }
}
