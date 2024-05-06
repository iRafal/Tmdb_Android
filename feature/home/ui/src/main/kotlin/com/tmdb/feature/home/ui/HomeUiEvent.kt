package com.tmdb.feature.home.ui

import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType

sealed interface HomeUiEvent {
    data object NavigateBack : HomeUiEvent
    data object ReloadAll : HomeUiEvent
    data class ReloadMovieSection(val movieSection: HomeMovieSectionType) : HomeUiEvent
    data class OpenMovie(val id: Int) : HomeUiEvent
}
