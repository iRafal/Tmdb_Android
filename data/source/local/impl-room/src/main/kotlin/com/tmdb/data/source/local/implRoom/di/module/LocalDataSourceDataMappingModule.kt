package com.tmdb.data.source.local.implRoom.di.module

import com.tmdb.data.source.local.implRoom.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.implRoom.mapping.MovieDataModelToEntityMapperImpl
import com.tmdb.data.source.local.implRoom.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.source.local.implRoom.mapping.MovieEntityToDataModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
interface LocalDataSourceDataMappingModule {
    @Binds
    fun movieEntityToDataModelMapper(impl: MovieEntityToDataModelMapperImpl): MovieEntityToDataModelMapper

    @Binds
    fun movieDataModelToEntityMapper(impl: MovieDataModelToEntityMapperImpl): MovieDataModelToEntityMapper
}

fun localDataSourceDataMappingModule() = module {
    factory<MovieEntityToDataModelMapper> { MovieEntityToDataModelMapperImpl() }
    factory<MovieDataModelToEntityMapper> { MovieDataModelToEntityMapperImpl() }
}
