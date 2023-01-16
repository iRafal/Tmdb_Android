package com.tmdb_test.data.db.room.util

import com.tmdb_test.data.db.room.movie.MovieEntity
import kotlinx.datetime.LocalDate

object ModelUtil {
    val movieEntity = MovieEntity(
        id = 550,
        title = "Fight Club",
        voteAverage = 7.8,
        releaseDate = LocalDate.parse("1999-10-12"),
        posterUrl = null,
    )
}