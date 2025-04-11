package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonListApiModelToDataStateModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface PersonMappingModule {
    @Binds
    fun personApiModelToDataModelMapper(
        impl: PersonApiModelToDataModelMapperImpl
    ): PersonApiModelToDataModelMapper

    @Binds
    fun personApiModelToDataStateModelMapper(
        impl: PersonApiModelToDataStateModelMapperImpl
    ): PersonApiModelToDataStateModelMapper

    @Binds
    fun personListApiModelToDataStateModelMapper(
        impl: PersonListApiModelToDataStateModelMapperImpl
    ): PersonListApiModelToDataStateModelMapper
}

