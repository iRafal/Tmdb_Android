package com.tmdb.store.di.module.env

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppEnvImpl
import org.koin.dsl.module

fun appEnvModule() = module {
    includes(appDbModule(), appNetworkModule())
    factory<AppEnv> {
        createAppEnvImpl(get<AppEnv.Network>(), get<AppEnv.Database>())
    }
}
