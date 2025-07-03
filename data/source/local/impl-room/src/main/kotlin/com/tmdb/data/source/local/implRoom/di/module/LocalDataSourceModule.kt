package com.tmdb.data.source.local.implRoom.di.module

import com.tmdb.data.db.room.di.module.DbModule
import com.tmdb.data.db.room.di.module.dbModule
import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.implRoom.MovieLocalDataSourceImpl
import com.tmdb.data.source.local.implRoom.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.implRoom.mapping.MovieEntityToDataModelMapper
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
