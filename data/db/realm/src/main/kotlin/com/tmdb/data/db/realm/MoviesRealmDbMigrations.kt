package com.tmdb.data.db.realm

import com.tmdb.data.db.realm.movie.MovieEntity
import io.realm.FieldAttribute.PRIMARY_KEY
import io.realm.RealmMigration

object MoviesRealmDbMigrations {
    fun migration0To1() = RealmMigration { realm, oldVersion, newVersion ->
        if (oldVersion < 1) {
            realm.schema.create(MovieEntity::class.java.simpleName).run {
                addField(MovieEntity.MOVIE_TABLE_COLUMN_ID, String::class.java, PRIMARY_KEY)
                addField(MovieEntity.MOVIE_TABLE_COLUMN_TITLE, String::class.java)
                addField(MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE, Double::class.java)
                addField(MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE, String::class.java)
                addField(MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL, String::class.java)
            }
        }
    }
    fun migration1To2() = RealmMigration { realm, oldVersion, newVersion ->
        if (oldVersion < 2) {
            checkNotNull(realm.schema.get(MovieEntity::class.java.simpleName))
                .addField(MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING, Boolean::class.java)
                .addField(MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR, Boolean::class.java)
                .addField(MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED, Boolean::class.java)
                .addField(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING, Boolean::class.java)
        }
    }
}
