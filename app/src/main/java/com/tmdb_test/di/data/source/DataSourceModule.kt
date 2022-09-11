package com.tmdb_test.di.data.source

import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSourceImpl
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSourceImpl
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSourceImpl
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSource
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun discoverRemoteDataSource(impl: DiscoverRemoteDataSourceImpl): DiscoverRemoteDataSource

    @Binds
    fun genreRemoteDataSource(impl: GenreRemoteDataSourceImpl): GenreRemoteDataSource

    @Binds
    fun movieRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    fun personRemoteDataSource(impl: PersonRemoteDataSourceImpl): PersonRemoteDataSource
}