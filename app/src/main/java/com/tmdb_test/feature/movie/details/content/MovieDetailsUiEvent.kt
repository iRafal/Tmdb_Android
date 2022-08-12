package com.tmdb_test.feature.movie.details.content

sealed interface MovieDetailsUiEvent {
    object NavigateBack : MovieDetailsUiEvent
}