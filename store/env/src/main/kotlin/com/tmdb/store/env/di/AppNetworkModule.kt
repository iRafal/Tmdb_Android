package com.tmdb.store.env.di

import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.store.env.contract.AppEnv.Network
import com.tmdb.store.env.impl.createAppNetworkEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppNetworkModule {

    @Provides
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