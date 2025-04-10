package com.tmdb.data.source.local.implRoom.di.module

import com.tmdb.data.db.room.module.DbModule
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.implRoom.MovieLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [LocalDataSourceDataMappingModule::class, DbModule::class])
interface LocalDataSourceModule {
    @Binds
    fun movieLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource
}
