package com.tmdb.feature.movie.details.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb.feature.movie.details.ui.MovieDetailsUiEvent.NavigateBack
import com.tmdb.feature.movie.details.ui.MovieDetailsUiState.Loading
import com.tmdb.ui.core.navigation.app.model.AppNavigation
import com.tmdb.ui.core.theme.TmdbTheme

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    TmdbTheme {
        val state = Loading
//        val state by movieDetailsViewModel.state.collectAsState(MovieDetailsState.Idle)
        val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> navController.navigate(AppNavigation.Close.route)
            }
        }
        MovieDetailsScreenUi(state, onEvent)
    }
}