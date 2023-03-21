package com.tmdb.ui.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb.ui.app.navigation.AppNavigation.Close
import com.tmdb.ui.core.theme.TmdbTheme
import com.tmdb.ui.details.MovieDetailsUiEvent.NavigateBack
import com.tmdb.ui.details.MovieDetailsUiState.Loading

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
                NavigateBack -> navController.navigate(Close.route)
            }
        }
        MovieDetailsScreenUi(state, onEvent)
    }
}