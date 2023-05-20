package com.tmdb.data.db.objectBox.di.modules

import android.content.Context
import com.tmdb.data.db.objectBox.di.ObjectBoxConfig
import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.db.objectBox.movie.dao.MovieDao
import com.tmdb.data.db.objectBox.movie.dao.MovieDaoImpl
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
