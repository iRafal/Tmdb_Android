package com.tmdb.store.di.module.env

import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.store.env.contract.AppEnv.Network
import com.tmdb.store.env.impl.createAppNetworkEnvImpl
import org.koin.dsl.module

fun appNetworkModule() = module {
    factory<Network> {
        createAppNetworkEnvImpl(
            get<DiscoverRemoteDataSource>(),
            get<GenreRemoteDataSource>(),
            get<MovieRemoteDataSource>(),
            get<PersonRemoteDataSource>(),
        )
    }
}
