package com.tmdb.data.local.impl_object_box.di

import com.tmdb.data.local.impl_object_box.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl_object_box.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.local.impl_object_box.mapping.movieDataModelToEntityMapperImpl
import com.tmdb.data.local.impl_object_box.mapping.movieEntityToDataModelMapperImpl
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
