package com.tmdb_test.data.db.room.di

import android.content.Context
import com.tmdb_test.data.db.room.MovieDb
import com.tmdb_test.data.db.room.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RoomDbModule::class]
)
object TestRoomDbModule {

    @Provides
    fun inMemoryDb(@ApplicationContext context: Context): MovieDb = MovieDb.getInMemoryDb(context)

    @Provides
    fun movieDao(database: MovieDb): MovieDao = database.movieDao()
}