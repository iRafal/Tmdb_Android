package com.tmdb_test.di.store.app.env

import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSource
import com.tmdb_test.store.env.AppEnv
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AppNetworkModule {

    @Provides
    fun appNetwork(
        discoverSource: DiscoverRemoteDataSource,
        genreSource: GenreRemoteDataSource,
        movieSource: MovieRemoteDataSource,
        personSource: PersonRemoteDataSource,
    ): AppEnv.Network {
        return object : AppEnv.Network {
            override val discoverSource = discoverSource
            override val genreSource = genreSource
            override val movieSource = movieSource
            override val personSource = personSource
        }
    }
}