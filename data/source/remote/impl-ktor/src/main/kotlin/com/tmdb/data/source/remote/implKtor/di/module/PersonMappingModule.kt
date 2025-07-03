package com.tmdb.data.source.remote.implKtor.di.module

import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.PersonListApiModelToDataStateModelMapperImpl
import org.koin.dsl.module

fun personMappingModule() = module {
    factory<PersonApiModelToDataModelMapper> { PersonApiModelToDataModelMapperImpl() }
    factory<PersonApiModelToDataStateModelMapper> { PersonApiModelToDataStateModelMapperImpl(get()) }
    factory<PersonListApiModelToDataStateModelMapper> { PersonListApiModelToDataStateModelMapperImpl(get()) }
}
