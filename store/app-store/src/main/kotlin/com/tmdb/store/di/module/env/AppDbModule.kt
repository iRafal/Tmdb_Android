package com.tmdb.store.di.module.env

import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import org.koin.dsl.module

fun appDbModule() = module {
    factory<AppEnv.Database> { createAppDbEnvImpl(get<MovieLocalDataSource>()) }
}
