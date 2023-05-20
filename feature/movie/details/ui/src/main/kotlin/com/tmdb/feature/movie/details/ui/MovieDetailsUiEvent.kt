package com.tmdb.feature.movie.details.ui

sealed interface MovieDetailsUiEvent {
    object NavigateBack : MovieDetailsUiEvent
}