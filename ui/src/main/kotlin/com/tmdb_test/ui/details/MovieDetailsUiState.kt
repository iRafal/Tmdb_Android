package com.tmdb_test.ui.details

import com.tmdb_test.ui.details.data.MovieDetailsUiData

sealed interface MovieDetailsUiState {
    object Idle : MovieDetailsUiState
    object Loading : MovieDetailsUiState
    data class Error(val cause: Throwable? = null) : MovieDetailsUiState
    data class NetworkError(val cause: Throwable? = null) : MovieDetailsUiState
    data class Success(val data: MovieDetailsUiData) : MovieDetailsUiState
}