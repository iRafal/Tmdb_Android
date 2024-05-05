package com.tmdb.feature.movie.details.ui

sealed interface MovieDetailsUiEvent {
    data object NavigateBack : MovieDetailsUiEvent
}
