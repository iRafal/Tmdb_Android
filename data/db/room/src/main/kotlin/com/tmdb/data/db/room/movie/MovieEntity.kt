package com.tmdb.data.db.room.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tmdb.data.db.room.movie.MovieEntity.Companion.MOVIE_TABLE_NAME
import kotlinx.datetime.LocalDate

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = MOVIE_TABLE_COLUMN_ID)
    val id: Int? = null,

    @ColumnInfo(name = MOVIE_TABLE_COLUMN_TITLE) val title: String? = null,
    @ColumnInfo(name = MOVIE_TABLE_COLUMN_VOTE_AVERAGE) val voteAverage: Double? = null,
    @ColumnInfo(name = MOVIE_TABLE_COLUMN_RELEASE_DATE) val releaseDate: LocalDate? = null,
    @ColumnInfo(name = MOVIE_TABLE_COLUMN_POSTER_URL) val posterUrl: String? = null,

    @ColumnInfo(
        name = MOVIE_TABLE_COLUMN_NOW_PLAYING,
        defaultValue = "0"
    ) val isNowPlaying: Boolean = false,

    @ColumnInfo(
        name = MOVIE_TABLE_COLUMN_NOW_POPULAR,
        defaultValue = "0"
    ) val isNowPopular: Boolean = false,

    @ColumnInfo(
        name = MOVIE_TABLE_COLUMN_TOP_RATED,
        defaultValue = "0"
    ) val isTopRated: Boolean = false,

    @ColumnInfo(
        name = MOVIE_TABLE_COLUMN_UPCOMING,
        defaultValue = "0"
    ) val isUpcoming: Boolean = false
) {
    companion object {
        const val MOVIE_TABLE_NAME = "movie"
        const val MOVIE_TABLE_COLUMN_ID = "id"
        const val MOVIE_TABLE_COLUMN_TITLE = "title"
        const val MOVIE_TABLE_COLUMN_VOTE_AVERAGE = "vote_average"
        const val MOVIE_TABLE_COLUMN_RELEASE_DATE = "release_date"
        const val MOVIE_TABLE_COLUMN_POSTER_URL = "poster_url"
        const val MOVIE_TABLE_COLUMN_NOW_PLAYING = "now_playing"
        const val MOVIE_TABLE_COLUMN_NOW_POPULAR = "now_popular"
        const val MOVIE_TABLE_COLUMN_TOP_RATED = "top_rated"
        const val MOVIE_TABLE_COLUMN_UPCOMING = "upcoming"
    }
}
