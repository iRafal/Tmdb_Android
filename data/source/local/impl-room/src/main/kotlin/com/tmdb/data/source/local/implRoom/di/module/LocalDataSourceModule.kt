package com.tmdb.data.source.local.implRoom.di.module

import com.tmdb.data.db.room.di.module.dbModule
import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.implRoom.MovieLocalDataSourceImpl
import com.tmdb.data.source.local.implRoom.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.implRoom.mapping.MovieEntityToDataModelMapper
import org.koin.dsl.module

fun localDataSourceModule() = module {
    includes(localDataSourceDataMappingModule(), dbModule())
    factory<MovieLocalDataSource> {
        MovieLocalDataSourceImpl(
            get<MovieDao>(),
            get<MovieEntityToDataModelMapper>(),
            get<MovieDataModelToEntityMapper>()
        )
    }
}
