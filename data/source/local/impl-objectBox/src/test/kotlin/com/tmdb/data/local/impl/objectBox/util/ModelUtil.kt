package com.tmdb.data.local.impl.objectBox.util

import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.model.MovieDataModel
import kotlinx.datetime.LocalDate

object ModelUtil {
    const val movieId = 550
    const val title = "Fight Club"
    const val voteAverage = 7.8
    val releaseDate = LocalDate.parse("1999-10-12")
    const val posterUrl = "https://web.page.com/posterUrl"
    const val isNowPlaying = false
    const val isNowPopular = false
    const val isTopRated = false
    const val isUpcoming = false

    val movieEntity = MovieEntity(
        movieId = movieId,
        title = title,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        posterUrl = posterUrl,
        isNowPlaying = isNowPlaying,
        isNowPopular = isNowPopular,
        isTopRated = isTopRated,
        isUpcoming = isUpcoming
    )

    val movieDataModel = MovieDataModel(
        id = movieId,
        title = title,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        posterUrl = posterUrl
    )
}
