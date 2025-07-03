package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.implKtor.discover.DiscoverRemoteDataSourceImpl
import com.tmdb.data.source.remote.implKtor.genre.GenreRemoteDataSourceImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.movie.MovieRemoteDataSourceImpl
import com.tmdb.data.source.remote.implKtor.person.PersonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

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

fun remoteDataSourceModule() = module {
    factory<DiscoverRemoteDataSource> { DiscoverRemoteDataSourceImpl(get(), get()) }
    factory<GenreRemoteDataSource> { GenreRemoteDataSourceImpl(get(), get()) }
    factory<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get(), get(), get()) }
    factory<PersonRemoteDataSource> { PersonRemoteDataSourceImpl(get(), get()) }
}
