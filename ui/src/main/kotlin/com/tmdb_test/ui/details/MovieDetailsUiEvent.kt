package com.tmdb_test.ui.details

sealed interface MovieDetailsUiEvent {
    object NavigateBack : MovieDetailsUiEvent
}