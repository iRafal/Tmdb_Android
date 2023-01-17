package com.tmdb_test.data.db.room.di

import android.content.Context
import com.tmdb_test.data.db.room.MovieDb
import com.tmdb_test.data.db.room.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomDbModule {
    @Provides
    @Singleton
    fun dataBase(@ApplicationContext appContext: Context): MovieDb = MovieDb.getInstance(appContext)

    @Provides
    @Singleton
    fun movieDao(database: MovieDb): MovieDao = database.movieDao()
}