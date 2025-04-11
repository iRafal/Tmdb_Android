package com.tmdb.data.source.local.impl.realm.di.module

import com.tmdb.data.source.local.impl.realm.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.impl.realm.mapping.MovieDataModelToEntityMapperImpl
import com.tmdb.data.source.local.impl.realm.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.source.local.impl.realm.mapping.MovieEntityToDataModelMapperImpl
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
