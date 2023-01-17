package com.tmdb_test.data.db.realm.movie

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.datetime.LocalDate

open class MovieEntity(
    @PrimaryKey var id: Int? = null,
    var title: String? = null,
    var voteAverage: Double? = null,
    var releaseDateAsString: String? = null,
    var posterUrl: String? = null,
    var isNowPlaying: Boolean = false,
    var isNowPopular: Boolean = false,
    var isTopRated: Boolean = false,
    var isUpcoming: Boolean = false
) : RealmObject() {
    var releaseDate: LocalDate?
        set(value) {
            releaseDateAsString = value?.toString()
        }
        get() = releaseDateAsString?.let { LocalDate.parse(it) }

    companion object {
        const val MOVIE_TABLE_COLUMN_ID = "id"
        const val MOVIE_TABLE_COLUMN_TITLE = "title"
        const val MOVIE_TABLE_COLUMN_VOTE_AVERAGE = "voteAverage"
        const val MOVIE_TABLE_COLUMN_RELEASE_DATE = "releaseDateAsString"
        const val MOVIE_TABLE_COLUMN_POSTER_URL = "posterUrl"
        const val MOVIE_TABLE_COLUMN_NOW_PLAYING = "isNowPlaying"
        const val MOVIE_TABLE_COLUMN_NOW_POPULAR = "isNowPopular"
        const val MOVIE_TABLE_COLUMN_TOP_RATED = "isTopRated"
        const val MOVIE_TABLE_COLUMN_UPCOMING = "isUpcoming"
    }
}