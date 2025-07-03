package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.api.config.di.module.imageUrlModule
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MovieApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.MoviesListApiModelToDataStateModelMapperImpl
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
    imageUrlModule()
    factory<MovieApiModelToDataModelMapper> { MovieApiModelToDataModelMapperImpl(get()) }
    factory<MovieApiModelToDataStateModelMapper> { MovieApiModelToDataStateModelMapperImpl(get()) }
    factory<MoviesListApiModelToDataStateModelMapper> { MoviesListApiModelToDataStateModelMapperImpl(get()) }
}
