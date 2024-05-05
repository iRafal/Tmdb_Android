package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapperImpl
import dagger.Binds
import dagger.Module


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
