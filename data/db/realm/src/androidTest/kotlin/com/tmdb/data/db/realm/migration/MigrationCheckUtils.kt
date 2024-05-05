package com.tmdb.data.db.realm.migration

import com.tmdb.data.db.realm.movie.MovieEntity
import io.realm.Realm
import io.realm.RealmFieldType.BOOLEAN
import io.realm.RealmFieldType.DOUBLE
import io.realm.RealmFieldType.INTEGER
import io.realm.RealmFieldType.STRING
import kotlin.test.assertEquals
import kotlin.test.assertTrue

fun Realm.checkV1Schema() {
    val schema = checkNotNull(schema.get(MovieEntity::class.java.simpleName))

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_ID))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_ID),
        actual = INTEGER
    )
    assertTrue(schema.isPrimaryKey(MovieEntity.MOVIE_TABLE_COLUMN_ID))

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_TITLE))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_TITLE),
        actual = STRING
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE),
        actual = DOUBLE
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE),
        actual = STRING
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL),
        actual = STRING
    )
}

fun Realm.checkV2Schema() {
    val schema = schema.get(MovieEntity::class.java.simpleName)
    checkNotNull(schema)

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING),
        actual = BOOLEAN
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR),
        actual = BOOLEAN
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED),
        actual = BOOLEAN
    )

    assertTrue(schema.hasField(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING))
    assertEquals(
        expected = schema.getFieldType(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING),
        actual = BOOLEAN
    )
}
