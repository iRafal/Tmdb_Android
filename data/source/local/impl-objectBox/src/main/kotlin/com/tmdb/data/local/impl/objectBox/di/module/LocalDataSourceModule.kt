package com.tmdb.data.local.impl.objectBox.di.module

import com.tmdb.data.db.objectBox.di.modules.dbModule
import com.tmdb.data.db.objectBox.movie.dao.MovieDao
import com.tmdb.data.local.impl.objectBox.MovieLocalDataSourceImpl
import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import org.koin.dsl.module

fun localDataSourceModule() = module {
    includes(localDataSourceDataMappingModule(), dbModule())
    factory<MovieLocalDataSource>() {
        MovieLocalDataSourceImpl(
            get<MovieDao>(),
            get<MovieEntityToDataModelMapper>(),
            get<MovieDataModelToEntityMapper>()
        )
    }
}
