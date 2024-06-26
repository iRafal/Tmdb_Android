package com.tmdb.data.db.realm.util

import com.tmdb.data.db.realm.movie.MovieEntity
import kotlinx.datetime.LocalDate

object ModelUtil {
    const val movieId = 550
    const val title = "Fight Club"
    const val voteAverage = 7.8
    private val releaseDate = LocalDate.parse("1999-10-12")
    const val posterUrl = "https://web.page.com/posterUrl"
    const val isNowPlaying = false
    const val isNowPopular = false
    const val isTopRated = false
    const val isUpcoming = false

    val movieEntity = MovieEntity(
        id = movieId,
        title = title,
        voteAverage = voteAverage,
        releaseDateAsString = null,
        posterUrl = posterUrl,
        isNowPlaying = isNowPlaying,
        isNowPopular = isNowPopular,
        isTopRated = isTopRated,
        isUpcoming = isUpcoming
    ).apply {
        releaseDate = ModelUtil.releaseDate
    }
}
