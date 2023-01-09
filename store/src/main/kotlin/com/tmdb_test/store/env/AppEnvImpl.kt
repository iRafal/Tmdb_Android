package com.tmdb_test.store.env

fun createAppEnvImpl(appNetwork: AppEnv.Network, appDatabase: AppEnv.Database): AppEnv {
    return object : AppEnv {
        override val network: AppEnv.Network = appNetwork
        override val database: AppEnv.Database = appDatabase
    }
}