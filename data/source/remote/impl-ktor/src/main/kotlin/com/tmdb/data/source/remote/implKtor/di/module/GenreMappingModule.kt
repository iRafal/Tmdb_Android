package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.GenreListApiModelToDataStateModelMapperImpl
import org.koin.dsl.module

fun genreMappingModule() = module {
    factory<GenreApiModelToDataModelMapper> { GenreApiModelToDataModelMapperImpl() }
    factory<GenreApiModelToDataStateModelMapper> { GenreApiModelToDataStateModelMapperImpl(get()) }
    factory<GenreListApiModelToDataStateModelMapper> { GenreListApiModelToDataStateModelMapperImpl(get()) }
}
