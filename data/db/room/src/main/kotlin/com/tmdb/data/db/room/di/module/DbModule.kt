package com.tmdb.data.db.room.di.module

import android.content.Context
import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Provides
    fun dataBase(@ApplicationContext appContext: Context): MovieDb = MovieDb.getInstance(appContext)

    @Provides
    fun movieDao(database: MovieDb): MovieDao = database.movieDao()
}

fun dbModule() = module {
    factory<MovieDb> { MovieDb.getInstance(get<Context>()) }
    factory<MovieDao> { get<MovieDb>().movieDao() }
}
