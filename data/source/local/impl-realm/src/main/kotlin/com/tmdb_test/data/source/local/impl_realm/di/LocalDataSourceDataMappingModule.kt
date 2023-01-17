package com.tmdb_test.data.source.local.impl_realm.di

import com.tmdb_test.data.source.local.impl_realm.mapping.MovieDataModelToEntityMapper
import com.tmdb_test.data.source.local.impl_realm.mapping.MovieEntityToDataModelMapper
import com.tmdb_test.data.source.local.impl_realm.mapping.movieDataModelToEntityMapperImpl
import com.tmdb_test.data.source.local.impl_realm.mapping.movieEntityToDataModelMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceDataMappingModule {
    @Provides
    fun movieEntityToDataModelMapper(): @JvmSuppressWildcards MovieEntityToDataModelMapper =
        ::movieEntityToDataModelMapperImpl

    @Provides
    fun movieDataModelToEntityMapper(): @JvmSuppressWildcards MovieDataModelToEntityMapper =
        ::movieDataModelToEntityMapperImpl
}