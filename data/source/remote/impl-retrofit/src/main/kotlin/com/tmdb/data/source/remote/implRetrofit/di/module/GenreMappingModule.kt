package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapperImpl
import org.koin.dsl.module

fun genreMappingModule() = module {
    factory<GenreApiModelToDataModelMapper> { GenreApiModelToDataModelMapperImpl() }
    factory<GenreApiModelToDataStateModelMapper> { GenreApiModelToDataStateModelMapperImpl(get()) }
    factory<GenreListApiModelToDataStateModelMapper> { GenreListApiModelToDataStateModelMapperImpl(get()) }
}
