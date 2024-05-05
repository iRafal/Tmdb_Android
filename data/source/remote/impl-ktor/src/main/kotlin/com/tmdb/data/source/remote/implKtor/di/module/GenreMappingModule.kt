package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapperImpl
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
