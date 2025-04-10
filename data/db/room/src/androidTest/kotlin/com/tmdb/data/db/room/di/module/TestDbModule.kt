package com.tmdb.data.db.room.di.module

import android.content.Context
import androidx.test.espresso.internal.inject.InstrumentationContext
import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.module.DbModule
import com.tmdb.data.db.room.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DbModule::class]
)
@Module
object TestDbModule {

    @Provides
    fun inMemoryDb(@InstrumentationContext context: Context): MovieDb = MovieDb.getInMemoryDb(context)

    @Provides
    fun movieDao(database: MovieDb): MovieDao = database.movieDao()
}
