package com.tmdb.store.env.impl

import com.tmdb.store.env.contract.AppEnv

fun createAppEnvImpl(appNetwork: AppEnv.Network, appDatabase: AppEnv.Database): AppEnv {
    return object : AppEnv {
        override val network: AppEnv.Network = appNetwork
        override val database: AppEnv.Database = appDatabase
    }
}