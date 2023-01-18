package com.tmdb_test.data.db.object_box.movie

import com.tmdb_test.data.db.object_box.movie.MovieEntity.Companion.MOVIE_TABLE_NAME
import com.tmdb_test.data.db.object_box.type.converters.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.NameInDb
import kotlinx.datetime.LocalDate

@Entity
@NameInDb(MOVIE_TABLE_NAME)
data class MovieEntity(
    @NameInDb(MOVIE_TABLE_COLUMN_ID)
    @Id var id: Long = 0,

    @NameInDb(MOVIE_TABLE_COLUMN_MOVIE_ID)
    val movieId: Int? = null,

    @NameInDb(MOVIE_TABLE_COLUMN_TITLE)
    val title: String? = null,

    @NameInDb(MOVIE_TABLE_COLUMN_VOTE_AVERAGE)
    val voteAverage: Double? = null,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    @NameInDb(MOVIE_TABLE_COLUMN_RELEASE_DATE)
    val releaseDate: LocalDate? = null,

    @NameInDb(MOVIE_TABLE_COLUMN_POSTER_URL)
    val posterUrl: String? = null,

    @NameInDb(MOVIE_TABLE_COLUMN_NOW_PLAYING)
    val isNowPlaying: Boolean = false,

    @NameInDb(MOVIE_TABLE_COLUMN_NOW_POPULAR)
    val isNowPopular: Boolean = false,

    @NameInDb(MOVIE_TABLE_COLUMN_TOP_RATED)
    val isTopRated: Boolean = false,

    @NameInDb(MOVIE_TABLE_COLUMN_UPCOMING)
    val isUpcoming: Boolean = false
) {
    companion object {
        const val MOVIE_TABLE_NAME = "movie"
        const val MOVIE_TABLE_COLUMN_ID = "id"
        const val MOVIE_TABLE_COLUMN_MOVIE_ID = "movie_id"
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