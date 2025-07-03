package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
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

fun genreMappingModule() = module {
    factory<GenreApiModelToDataModelMapper> { GenreApiModelToDataModelMapperImpl() }
    factory<GenreApiModelToDataStateModelMapper> { GenreApiModelToDataStateModelMapperImpl(get()) }
    factory<GenreListApiModelToDataStateModelMapper> { GenreListApiModelToDataStateModelMapperImpl(get()) }
}
