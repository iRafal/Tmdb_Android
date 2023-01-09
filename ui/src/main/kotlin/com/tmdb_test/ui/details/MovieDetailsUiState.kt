package com.tmdb_test.ui.details

import com.tmdb_test.ui.details.data.MovieDetailsUiData

sealed interface MovieDetailsUiState {
    object Idle : MovieDetailsUiState
    object Loading : MovieDetailsUiState
    class Error(val cause: Throwable? = null) : MovieDetailsUiState
    class NetworkError(val cause: Throwable? = null) : MovieDetailsUiState
    class Success(val data: MovieDetailsUiData) : MovieDetailsUiState
}