package com.tmdb.data.source.local.implRoom.di.module

import com.tmdb.data.source.local.implRoom.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.implRoom.mapping.MovieDataModelToEntityMapperImpl
import com.tmdb.data.source.local.implRoom.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.source.local.implRoom.mapping.MovieEntityToDataModelMapperImpl
import org.koin.dsl.module

fun localDataSourceDataMappingModule() = module {
    factory<MovieEntityToDataModelMapper> { MovieEntityToDataModelMapperImpl() }
    factory<MovieDataModelToEntityMapper> { MovieDataModelToEntityMapperImpl() }
}
