package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.implRetrofit.discover.DiscoverRemoteDataSourceImpl
import com.tmdb.data.source.remote.implRetrofit.genre.GenreRemoteDataSourceImpl
import com.tmdb.data.source.remote.implRetrofit.movie.MovieRemoteDataSourceImpl
import com.tmdb.data.source.remote.implRetrofit.person.PersonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [MovieMappingModule::class, PersonMappingModule::class, GenreMappingModule::class])
interface RemoteDataSourceModule {
    @Binds
    fun discoverRemoteDataSource(impl: DiscoverRemoteDataSourceImpl): DiscoverRemoteDataSource

    @Binds
    fun genreRemoteDataSource(impl: GenreRemoteDataSourceImpl): GenreRemoteDataSource

    @Binds
    fun movieRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    fun personRemoteDataSource(impl: PersonRemoteDataSourceImpl): PersonRemoteDataSource
}
