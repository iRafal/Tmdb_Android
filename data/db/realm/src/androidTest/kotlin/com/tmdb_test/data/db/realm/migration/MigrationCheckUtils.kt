package com.tmdb_test.data.db.realm.migration

import com.tmdb_test.data.db.realm.movie.MovieEntity
import io.realm.Realm
import io.realm.RealmFieldType.BOOLEAN
import io.realm.RealmFieldType.DOUBLE
import io.realm.RealmFieldType.INTEGER
import io.realm.RealmFieldType.STRING
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

fun Realm.checkV1Schema() {
    val schema = checkNotNull(schema.get(MovieEntity::class.java.simpleName))

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_ID))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_ID),
        INTEGER
    )
    assertTrue(schema.isPrimaryKey(MovieEntity.MOVIE_TABLE_COLUMN_ID))

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_TITLE))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_TITLE),
        STRING
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE),
        DOUBLE
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE),
        STRING
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL),
        STRING
    )
}

fun Realm.checkV2Schema() {
    val schema = schema.get(MovieEntity::class.java.simpleName)
    checkNotNull(schema)

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING),
        BOOLEAN
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR),
        BOOLEAN
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED),
        BOOLEAN
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING))
    assertEquals(
        schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING),
        BOOLEAN
    )
}