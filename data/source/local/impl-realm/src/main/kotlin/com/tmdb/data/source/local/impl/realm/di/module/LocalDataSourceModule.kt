package com.tmdb.data.source.local.impl.realm.di.module

import com.tmdb.data.db.realm.di.modules.DbModule
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.impl.realm.MovieLocalDataSourceImpl
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
