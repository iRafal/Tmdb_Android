package com.tmdb_test.store.env

import com.tmdb_test.data.source.remote.contract.MovieLocalDataSource
import com.tmdb_test.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.contract.person.PersonRemoteDataSource

interface AppEnv {
    val network: Network
    val database: Database

    interface Network {
        val discoverSource: DiscoverRemoteDataSource
        val genreSource: GenreRemoteDataSource
        val movieSource: MovieRemoteDataSource
        val personSource: PersonRemoteDataSource
    }

    interface Database {
        val movieSource: MovieLocalDataSource
    }
}
