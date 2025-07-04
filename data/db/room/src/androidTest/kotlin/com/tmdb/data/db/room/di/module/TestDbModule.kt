package com.tmdb.data.db.room.di.module

import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.movie.MovieDao
import org.koin.dsl.module

fun testDbModule() = module {
    factory<MovieDb> { MovieDb.getInMemoryDb(get()) }
    factory<MovieDao> { get<MovieDb>().movieDao() }
}
