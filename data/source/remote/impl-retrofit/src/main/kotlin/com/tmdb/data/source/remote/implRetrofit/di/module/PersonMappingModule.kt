package com.tmdb.data.source.remote.implRetrofit.di.module

import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataStateModelMapperImpl
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonListApiModelToDataStateModelMapperImpl
import org.koin.dsl.module

fun personMappingModule() = module {
    factory<PersonApiModelToDataModelMapper> { PersonApiModelToDataModelMapperImpl() }
    factory<PersonApiModelToDataStateModelMapper> { PersonApiModelToDataStateModelMapperImpl(get()) }
    factory<PersonListApiModelToDataStateModelMapper> { PersonListApiModelToDataStateModelMapperImpl(get()) }
}
