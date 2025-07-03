package com.tmdb.data.db.room.di.module

import android.content.Context
import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.movie.MovieDao
import org.koin.dsl.module

fun dbModule() = module {
    factory<MovieDb> { MovieDb.getInstance(get<Context>()) }
    factory<MovieDao> { get<MovieDb>().movieDao() }
}
