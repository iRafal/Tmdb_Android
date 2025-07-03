package com.tmdb.data.db.objectBox.di.modules

import android.content.Context
import com.tmdb.data.db.objectBox.di.ObjectBoxConfig
import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.db.objectBox.movie.dao.MovieDao
import com.tmdb.data.db.objectBox.movie.dao.MovieDaoImpl
import io.objectbox.Box
import io.objectbox.BoxStore
import org.koin.dsl.module

fun dbModule() = module {
    factory<BoxStore> { ObjectBoxConfig.store(get<Context>()) }
    factory<Box<MovieEntity>> { ObjectBoxConfig.moviesBox(get<BoxStore>()) }
    factory<MovieDao> { MovieDaoImpl(get<Box<MovieEntity>>()) }
}
