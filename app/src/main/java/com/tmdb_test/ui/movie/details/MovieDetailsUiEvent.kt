package com.tmdb_test.ui.movie.details

sealed interface MovieDetailsUiEvent {
    object NavigateBack : MovieDetailsUiEvent
}