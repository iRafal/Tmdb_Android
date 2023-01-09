package com.tmdb_test.ui.home.data

import com.tmdb_test.ui.util.data.UiState

data class HomeUiData(val movieSections: Map<HomeMovieSection, UiState<List<Movie>>>) {
    data class Movie(
        val id: Int,
        val title: String,
        val averageVote: Double,
        val releaseDate: String,
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

