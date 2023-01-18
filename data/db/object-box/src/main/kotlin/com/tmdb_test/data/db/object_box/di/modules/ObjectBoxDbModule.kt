package com.tmdb_test.data.db.object_box.di.modules

import android.content.Context
import com.tmdb_test.data.db.object_box.di.ObjectBoxConfig
import com.tmdb_test.data.db.object_box.movie.MovieEntity
import com.tmdb_test.data.db.object_box.movie.dao.MovieDao
import com.tmdb_test.data.db.object_box.movie.dao.MovieDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ObjectBoxDbModule {
    @Provides
    @Singleton
    fun dataBase(@ApplicationContext appContext: Context): BoxStore {
        return ObjectBoxConfig.store(appContext)
    }

    @Provides
    @Singleton
    fun movieBox(boxStore: BoxStore): Box<MovieEntity> = ObjectBoxConfig.moviesBox(boxStore)

    @Provides
    @Singleton
    fun movieDao(impl: MovieDaoImpl): MovieDao = impl
}