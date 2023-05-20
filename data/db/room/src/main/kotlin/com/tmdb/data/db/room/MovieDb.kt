package com.tmdb.data.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tmdb.data.db.room.MovieDbMigrations.MIGRATION_1_2
import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.data.db.room.movie.MovieEntity
import com.tmdb.data.db.room.type.converters.LocalDateConverter
import com.tmdb.data.db.room.type.converters.LocalDateTimeConverter

@Database(
    entities = [MovieEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(
    LocalDateTimeConverter::class,
    LocalDateConverter::class
)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private const val DB_NAME = "Movie.db"
        fun getDbBuilder(context: Context, dbName: String): Builder<MovieDb> {
            return Room.databaseBuilder(context.applicationContext, MovieDb::class.java, dbName)
        }

        fun getInstance(context: Context): MovieDb = getDbBuilder(context, DB_NAME).build()

        fun getInMemoryDbBuilder(context: Context): Builder<MovieDb> =
            Room.inMemoryDatabaseBuilder(context, MovieDb::class.java).addMigrations(MIGRATION_1_2)

        fun getInMemoryDb(context: Context): MovieDb = getInMemoryDbBuilder(context).build()
    }
}
