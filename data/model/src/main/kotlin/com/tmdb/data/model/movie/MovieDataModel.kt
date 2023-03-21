package com.tmdb.data.model.movie

import kotlinx.datetime.LocalDate

data class MovieDataModel(
    val id: Int? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val releaseDate: LocalDate? = null,
    val posterUrl: String? = null,
)