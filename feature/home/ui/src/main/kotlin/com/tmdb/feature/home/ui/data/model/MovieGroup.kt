package com.tmdb.feature.home.ui.data.model

import androidx.annotation.StringRes

data class MovieGroup(
    @StringRes val title: Int,
    val type: HomeMovieSectionType,
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: Error? = null
) {
    enum class Error {
        NetworkError, OtherError
    }
}
