package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapperImpl
import dagger.Binds
import dagger.Module


@Module
interface GenreMappingModule {
    @Binds
    fun genreApiModelToDataModelMapper(
        impl: GenreApiModelToDataModelMapperImpl
    ): GenreApiModelToDataModelMapper

    @Binds
    fun genreApiModelToDataStateModelMapper(
        impl: GenreApiModelToDataStateModelMapperImpl
    ): GenreApiModelToDataStateModelMapper

    @Binds
    fun genreListApiModelToDataStateModelMapper(
        impl: GenreListApiModelToDataStateModelMapperImpl
    ): GenreListApiModelToDataStateModelMapper
}
