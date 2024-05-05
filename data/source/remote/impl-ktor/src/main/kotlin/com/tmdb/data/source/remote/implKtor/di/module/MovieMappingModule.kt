package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapperImpl
import dagger.Binds
import dagger.Module

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
