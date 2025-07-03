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
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Provides
    fun dataBase(@ApplicationContext appContext: Context): BoxStore = ObjectBoxConfig.store(appContext)

    @Provides
    fun movieBox(boxStore: BoxStore): Box<MovieEntity> = ObjectBoxConfig.moviesBox(boxStore)

    @Provides
    fun movieDao(impl: MovieDaoImpl): MovieDao = impl
}

fun dbModule() = module {
    factory<BoxStore> { ObjectBoxConfig.store(get<Context>()) }
    factory<Box<MovieEntity>> { ObjectBoxConfig.moviesBox(get<BoxStore>()) }
    factory<MovieDao> { MovieDaoImpl(get<Box<MovieEntity>>()) }
}
