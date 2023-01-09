package com.tmdb_test.store.di.modules.env

import com.tmdb_test.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.env.createAppNetworkEnvImpl
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
    ): AppEnv.Network = createAppNetworkEnvImpl(
        discoverSource,
        genreSource,
        movieSource,
        personSource,
    )
}