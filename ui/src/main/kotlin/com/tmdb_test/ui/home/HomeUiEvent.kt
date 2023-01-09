package com.tmdb_test.ui.home

import com.tmdb_test.ui.home.data.HomeMovieSection

sealed interface HomeUiEvent {
    object NavigateBack : HomeUiEvent
    class ReloadMovieSection(val movieSection: HomeMovieSection): HomeUiEvent
    class OpenMovie(val id: Int): HomeUiEvent
}