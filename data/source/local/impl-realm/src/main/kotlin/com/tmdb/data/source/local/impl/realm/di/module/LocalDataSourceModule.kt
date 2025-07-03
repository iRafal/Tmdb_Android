package com.tmdb.data.source.local.impl.realm.di.module

import com.tmdb.data.db.realm.di.modules.dbModule
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.impl.realm.MovieLocalDataSourceImpl
import com.tmdb.data.source.local.impl.realm.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.impl.realm.mapping.MovieEntityToDataModelMapper
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
