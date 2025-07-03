package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

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

fun personMappingModule() = module {
    factory<PersonApiModelToDataModelMapper> { PersonApiModelToDataModelMapperImpl() }
    factory<PersonApiModelToDataStateModelMapper> { PersonApiModelToDataStateModelMapperImpl(get()) }
    factory<PersonListApiModelToDataStateModelMapper> { PersonListApiModelToDataStateModelMapperImpl(get()) }
}
