package com.tmdb_test.store.env.di.modules

import com.tmdb_test.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb_test.store.env.AppEnv.Network
import com.tmdb_test.store.env.createAppNetworkEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppNetworkModule {

    @Provides
    @JvmStatic
    fun appNetwork(
        discoverSource: DiscoverRemoteDataSource,
        genreSource: GenreRemoteDataSource,
        movieSource: MovieRemoteDataSource,
        personSource: PersonRemoteDataSource,
    ): Network = createAppNetworkEnvImpl(
        discoverSource,
        genreSource,
        movieSource,
        personSource,
    )
}