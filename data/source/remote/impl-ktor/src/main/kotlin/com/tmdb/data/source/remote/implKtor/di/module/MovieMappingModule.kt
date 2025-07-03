package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapperImpl
import org.koin.dsl.module

fun movieMappingModule() = module {
    factory<MovieApiModelToDataModelMapper> { MovieApiModelToDataModelMapperImpl(get()) }
    factory<MovieApiModelToDataStateModelMapper> { MovieApiModelToDataStateModelMapperImpl(get()) }
    factory<MoviesListApiModelToDataStateModelMapper> { MoviesListApiModelToDataStateModelMapperImpl(get()) }
}
