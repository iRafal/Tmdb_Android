package com.tmdb.feature.movie.details.ui.util

import com.tmdb.data.model.MovieDataModel
import kotlinx.datetime.LocalDate

object ModelUtil {
    val movieDataModel = MovieDataModel(
        id = 550,
        title = "Fight Club",
        voteAverage = 7.8,
        releaseDate = LocalDate.parse("1999-10-12"),
        posterUrl = null
    )
}
