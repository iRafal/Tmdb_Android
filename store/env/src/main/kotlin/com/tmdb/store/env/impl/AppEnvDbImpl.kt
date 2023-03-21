package com.tmdb.store.env.impl

import com.tmdb.data.source.remote.contract.MovieLocalDataSource
import com.tmdb.store.env.contract.AppEnv

fun createAppDbEnvImpl(
    movieSource: MovieLocalDataSource
): AppEnv.Database = object : AppEnv.Database {
    override val movieSource: MovieLocalDataSource = movieSource
}