package com.tmdb.data.local.impl.objectBox.di.module

import com.tmdb.data.db.objectBox.di.modules.DbModule
import com.tmdb.data.db.objectBox.movie.dao.MovieDao
import com.tmdb.data.local.impl.objectBox.MovieLocalDataSourceImpl
import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.source.local.contract.MovieLocalDataSource
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
    factory<MovieLocalDataSource>() {
        MovieLocalDataSourceImpl(get<MovieDao>(), get<MovieEntityToDataModelMapper>(), get<MovieDataModelToEntityMapper>())
    }
}
