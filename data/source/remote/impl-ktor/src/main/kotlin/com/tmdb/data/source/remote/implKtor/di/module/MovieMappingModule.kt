package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
interface MovieMappingModule {
    @Binds
    fun movieApiToDataModelMapper(
        impl: MovieApiModelToDataModelMapperImpl
    ): MovieApiModelToDataModelMapper

    @Binds
    fun movieApiModelToDataStateModelMapper(
        impl: MovieApiModelToDataStateModelMapperImpl
    ): MovieApiModelToDataStateModelMapper

    @Binds
    fun moviesApiToDataStateMapper(
        impl: MoviesListApiModelToDataStateModelMapperImpl
    ): MoviesListApiModelToDataStateModelMapper
}

fun movieMappingModule() = module {
    factory<MovieApiModelToDataModelMapper> { MovieApiModelToDataModelMapperImpl(get()) }
    factory<MovieApiModelToDataStateModelMapper> { MovieApiModelToDataStateModelMapperImpl(get()) }
    factory<MoviesListApiModelToDataStateModelMapper> { MoviesListApiModelToDataStateModelMapperImpl(get()) }
}