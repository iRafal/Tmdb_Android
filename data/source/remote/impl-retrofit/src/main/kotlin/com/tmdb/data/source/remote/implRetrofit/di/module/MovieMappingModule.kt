package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.api.config.di.module.imageUrlModule
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MoviesListApiModelToDataStateModelMapperImpl
import org.koin.dsl.module

fun movieMappingModule() = module {
    includes(imageUrlModule())
    factory<MovieApiModelToDataModelMapper> { MovieApiModelToDataModelMapperImpl(get()) }
    factory<MovieApiModelToDataStateModelMapper> { MovieApiModelToDataStateModelMapperImpl(get()) }
    factory<MoviesListApiModelToDataStateModelMapper> { MoviesListApiModelToDataStateModelMapperImpl(get()) }
}
