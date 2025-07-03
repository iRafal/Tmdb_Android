package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.implKtor.discover.DiscoverRemoteDataSourceImpl
import com.tmdb.data.source.remote.implKtor.genre.GenreRemoteDataSourceImpl
import com.tmdb.data.source.remote.implKtor.movie.MovieRemoteDataSourceImpl
import com.tmdb.data.source.remote.implKtor.person.PersonRemoteDataSourceImpl
import org.koin.dsl.module

fun remoteDataSourceModule() = module {
    factory<DiscoverRemoteDataSource> { DiscoverRemoteDataSourceImpl(get(), get()) }
    factory<GenreRemoteDataSource> { GenreRemoteDataSourceImpl(get(), get()) }
    factory<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get(), get(), get()) }
    factory<PersonRemoteDataSource> { PersonRemoteDataSourceImpl(get(), get()) }
}
