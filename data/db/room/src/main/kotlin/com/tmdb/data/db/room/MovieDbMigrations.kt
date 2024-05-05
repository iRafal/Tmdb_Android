package com.tmdb.data.db.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tmdb.data.db.room.movie.MovieEntity

object MovieDbMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            val movieTable = MovieEntity.MOVIE_TABLE_NAME
            db.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING}` INTEGER NOT NULL DEFAULT 0;"
            )
            db.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR}` INTEGER NOT NULL DEFAULT 0;"
            )
            db.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED}` INTEGER NOT NULL DEFAULT 0;"
            )
            db.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING}` INTEGER NOT NULL DEFAULT 0;"
            )
        }
    }
}
