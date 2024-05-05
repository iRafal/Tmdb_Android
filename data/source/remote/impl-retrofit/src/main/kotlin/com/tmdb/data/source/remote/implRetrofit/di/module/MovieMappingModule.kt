package com.tmdb.data.source.remote.implRetrofit.di.module


import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MoviesListApiModelToDataStateModelMapperImpl
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
