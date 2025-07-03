package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.implRetrofit.discover.DiscoverRemoteDataSourceImpl
import com.tmdb.data.source.remote.implRetrofit.genre.GenreRemoteDataSourceImpl
import com.tmdb.data.source.remote.implRetrofit.movie.MovieRemoteDataSourceImpl
import com.tmdb.data.source.remote.implRetrofit.person.PersonRemoteDataSourceImpl
import org.koin.dsl.module

fun remoteDataSourceModule() = module {
    includes(
        movieMappingModule(),
        personMappingModule(),
        genreMappingModule()
    )

    factory<DiscoverRemoteDataSource> { DiscoverRemoteDataSourceImpl(get(), get()) }
    factory<GenreRemoteDataSource> { GenreRemoteDataSourceImpl(get(), get()) }
    factory<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get(), get(), get()) }
    factory<PersonRemoteDataSource> { PersonRemoteDataSourceImpl(get(), get()) }
}
