package com.tmdb_test.store.env

import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSource

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
    }
}
