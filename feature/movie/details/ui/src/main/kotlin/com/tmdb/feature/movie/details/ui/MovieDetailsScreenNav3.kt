package com.tmdb.feature.movie.details.ui

import androidx.compose.runtime.Composable
import com.tmdb.feature.movie.details.ui.MovieDetailsUiEvent.NavigateBack
import com.tmdb.feature.movie.details.ui.MovieDetailsUiState.Loading

@Composable
fun MovieDetailsScreenNav3(back: () -> Unit, viewModel: MovieDetailsViewModelNav3) {
    val state = Loading
//        val state by viewModel.state.collectAsState(MovieDetailsState.Idle)
    val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
        when (event) {
            NavigateBack -> back()
        }
    }
    MovieDetailsScreenUi(state, onEvent)
}
