package com.tmdb_test.feature.home.data

import com.tmdb_test.feature.home.content.HomeMovieSection
import com.tmdb_test.feature.home.content.HomeMovieSection.NOW_PLAYING
import com.tmdb_test.feature.home.content.HomeMovieSection.NOW_POPULAR
import com.tmdb_test.feature.home.content.HomeMovieSection.TOP_RATED
import com.tmdb_test.feature.home.content.HomeMovieSection.UPCOMING
import com.tmdb_test.ui.util.UiState
import com.tmdb_test.ui.util.UiState.Loading

data class HomeUiData(
    val movieSections: Map<HomeMovieSection, UiState<List<Movie>>>
) {
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
                NOW_PLAYING to Loading(),
                NOW_POPULAR to Loading(),
                TOP_RATED to Loading(),
                UPCOMING to Loading(),
            )
        )
    }
}

