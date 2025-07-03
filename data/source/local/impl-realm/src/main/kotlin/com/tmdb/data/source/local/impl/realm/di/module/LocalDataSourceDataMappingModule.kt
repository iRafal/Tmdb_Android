package com.tmdb.data.source.local.impl.realm.di.module

import com.tmdb.data.source.local.impl.realm.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.impl.realm.mapping.MovieDataModelToEntityMapperImpl
import com.tmdb.data.source.local.impl.realm.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.source.local.impl.realm.mapping.MovieEntityToDataModelMapperImpl
import org.koin.dsl.module

fun localDataSourceDataMappingModule() = module {
    factory<MovieEntityToDataModelMapper> { MovieEntityToDataModelMapperImpl() }
    factory<MovieDataModelToEntityMapper> { MovieDataModelToEntityMapperImpl() }
}
