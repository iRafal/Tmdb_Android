package com.tmdb.data.source.local.impl.realm.di.module

import com.tmdb.data.db.realm.di.modules.DbModule
import com.tmdb.data.db.realm.di.modules.dbModule
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.impl.realm.MovieLocalDataSourceImpl
import com.tmdb.data.source.local.impl.realm.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.impl.realm.mapping.MovieEntityToDataModelMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [LocalDataSourceDataMappingModule::class, DbModule::class])
interface LocalDataSourceModule {
    @Binds
    fun movieLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource
}

fun localDataSourceModule() = module {
    localDataSourceDataMappingModule()
    dbModule()
    factory<MovieLocalDataSource> {
        MovieLocalDataSourceImpl(
            get<MovieDao>(),
            get<MovieEntityToDataModelMapper>(),
            get<MovieDataModelToEntityMapper>()
        )
    }
}
