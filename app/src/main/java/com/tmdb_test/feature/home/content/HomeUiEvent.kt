package com.tmdb_test.feature.home.content

sealed interface HomeUiEvent {
    object NavigateBack : HomeUiEvent
    class ReloadMovieSection(val movieSection: HomeMovieSection): HomeUiEvent
    class OpenMovie(val id: Int): HomeUiEvent
}