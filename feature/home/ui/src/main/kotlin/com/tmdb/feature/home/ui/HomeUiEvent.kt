package com.tmdb.feature.home.ui

import com.tmdb.feature.home.ui.data.model.HomeMovieSection

sealed interface HomeUiEvent {
    data object NavigateBack : HomeUiEvent
    data class ReloadMovieSection(val movieSection: HomeMovieSection) : HomeUiEvent
    data class OpenMovie(val id: Int) : HomeUiEvent
}
