package com.tmdb.feature.movie.details.ui

import com.tmdb.feature.movie.details.ui.model.MovieDetailsUiData

sealed interface MovieDetailsUiState {
    data object Idle : MovieDetailsUiState
    data object Loading : MovieDetailsUiState
    data class Error(val cause: Throwable? = null) : MovieDetailsUiState
    data class NetworkError(val cause: Throwable? = null) : MovieDetailsUiState
    data class Success(val data: MovieDetailsUiData) : MovieDetailsUiState
}
