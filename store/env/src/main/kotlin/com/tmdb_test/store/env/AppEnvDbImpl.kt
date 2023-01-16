package com.tmdb_test.store.env

import com.tmdb_test.data.source.remote.contract.MovieLocalDataSource

fun createAppDbEnvImpl(
    movieSource: MovieLocalDataSource
): AppEnv.Database = object : AppEnv.Database {
    override val movieSource: MovieLocalDataSource = movieSource
}