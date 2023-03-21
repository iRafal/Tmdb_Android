package com.tmdb.ui.details

sealed interface MovieDetailsUiEvent {
    object NavigateBack : MovieDetailsUiEvent
}