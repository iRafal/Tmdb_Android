package com.tmdb.ui.home.data

import com.tmdb.ui.core.data.UiState
import kotlinx.datetime.LocalDate

data class HomeUiData(val movieSections: Map<HomeMovieSection, UiState<List<Movie>>>) {
    data class Movie(
        val id: Int,
        val title: String,
        val averageVote: Double,
        val releaseDate: LocalDate?,
        val posterUrl: String?
    )

    companion object {
        val INITIAL = HomeUiData(
            movieSections = mapOf(
                HomeMovieSection.NOW_PLAYING to UiState.Loading(),
                HomeMovieSection.NOW_POPULAR to UiState.Loading(),
                HomeMovieSection.TOP_RATED to UiState.Loading(),
                HomeMovieSection.UPCOMING to UiState.Loading(),
            )
        )
    }
}

