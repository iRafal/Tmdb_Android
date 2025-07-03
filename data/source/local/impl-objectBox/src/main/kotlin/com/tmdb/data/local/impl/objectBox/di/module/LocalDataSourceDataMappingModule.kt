package com.tmdb.data.local.impl.objectBox.di.module

import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapperImpl
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapperImpl
import org.koin.dsl.module

fun localDataSourceDataMappingModule() = module {
    factory<MovieEntityToDataModelMapper> { MovieEntityToDataModelMapperImpl() }
    factory<MovieDataModelToEntityMapper> { MovieDataModelToEntityMapperImpl() }
}
