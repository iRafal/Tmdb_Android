package com.tmdb.feature.home.ui.util

import com.tmdb.data.model.MovieDataModel
import com.tmdb.feature.home.ui.data.model.Movie
import kotlinx.datetime.LocalDate

object ModelUtil {
    val movieDataModel = MovieDataModel(
        id = 550,
        title = "Fight Club",
        voteAverage = 7.8,
        releaseDate = LocalDate.parse("1999-10-12"),
        posterUrl = null
    )

    val uiModelMovie = Movie(
        id = 550,
        title = "Fight Club",
        averageVote = 7.8,
        releaseDate = LocalDate.parse("1999-10-12"),
        posterUrl = null
    )
}
