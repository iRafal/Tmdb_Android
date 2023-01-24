package com.tmdb_test.store.env.impl

import com.tmdb_test.data.source.remote.contract.MovieLocalDataSource
import com.tmdb_test.store.env.contract.AppEnv

fun createAppDbEnvImpl(
    movieSource: MovieLocalDataSource
): AppEnv.Database = object : AppEnv.Database {
    override val movieSource: MovieLocalDataSource = movieSource
}