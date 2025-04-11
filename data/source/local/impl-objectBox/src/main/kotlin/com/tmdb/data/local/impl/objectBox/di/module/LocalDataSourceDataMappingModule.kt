package com.tmdb.data.local.impl.objectBox.di.module

import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapperImpl
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface LocalDataSourceDataMappingModule {
    @Binds
    fun movieEntityToDataModelMapper(impl: MovieEntityToDataModelMapperImpl): MovieEntityToDataModelMapper

    @Binds
    fun movieDataModelToEntityMapper(impl: MovieDataModelToEntityMapperImpl): MovieDataModelToEntityMapper
}
