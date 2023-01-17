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


    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (voteAverage?.hashCode() ?: 0)
        result = 31 * result + (releaseDateAsString?.hashCode() ?: 0)
        result = 31 * result + (posterUrl?.hashCode() ?: 0)
        result = 31 * result + isNowPlaying.hashCode()
        result = 31 * result + isNowPopular.hashCode()
        result = 31 * result + isTopRated.hashCode()
        result = 31 * result + isUpcoming.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MovieEntity) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (voteAverage != other.voteAverage) return false
        if (releaseDateAsString != other.releaseDateAsString) return false
        if (posterUrl != other.posterUrl) return false
        if (isNowPlaying != other.isNowPlaying) return false
        if (isNowPopular != other.isNowPopular) return false
        if (isTopRated != other.isTopRated) return false
        if (isUpcoming != other.isUpcoming) return false

        return true
    }

    override fun toString(): String {
        return "MovieEntity(id=$id, title=$title, voteAverage=$voteAverage, releaseDateAsString=$releaseDateAsString, posterUrl=$posterUrl, isNowPlaying=$isNowPlaying, isNowPopular=$isNowPopular, isTopRated=$isTopRated, isUpcoming=$isUpcoming)"
    }

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