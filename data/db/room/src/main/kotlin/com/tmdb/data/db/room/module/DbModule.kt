package com.tmdb.data.db.room.module

import android.content.Context
import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.util.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Provides
    fun dataBase(@ApplicationContext appContext: Context): MovieDb = MovieDb.getInstance(appContext)

    @Provides
    fun movieDao(database: MovieDb): MovieDao = database.movieDao()
}
