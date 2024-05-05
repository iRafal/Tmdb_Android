package com.tmdb.data.local.impl.objectBox.di.module

import com.tmdb.data.db.objectBox.di.modules.DbModule
import com.tmdb.data.local.impl.objectBox.MovieLocalDataSourceImpl
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import dagger.Binds
import dagger.Module


@Module(includes = [LocalDataSourceDataMappingModule::class, DbModule::class])
interface LocalDataSourceModule {
    @Binds
    fun movieLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource
}
