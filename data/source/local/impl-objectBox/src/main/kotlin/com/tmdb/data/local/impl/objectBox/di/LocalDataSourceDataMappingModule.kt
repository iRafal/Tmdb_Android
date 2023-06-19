package com.tmdb.data.local.impl.objectBox.di

import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.local.impl.objectBox.mapping.movieDataModelToEntityMapperImpl
import com.tmdb.data.local.impl.objectBox.mapping.movieEntityToDataModelMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
object LocalDataSourceDataMappingModule {
    @Provides
    fun movieEntityToDataModelMapper(): @JvmSuppressWildcards MovieEntityToDataModelMapper =
        ::movieEntityToDataModelMapperImpl

    @Provides
    fun movieDataModelToEntityMapper(): @JvmSuppressWildcards MovieDataModelToEntityMapper =
        ::movieDataModelToEntityMapperImpl
}
