package com.tmdb_test.ui.movie.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tmdb_test.ui.movie.details.MovieDetailsUiEvent.NavigateBack
import com.tmdb_test.ui.movie.details.MovieDetailsUiState.Loading
import com.tmdb_test.ui.navigation.AppNavigation
import com.tmdb_test.ui.theme.Tmdb_TestTheme

@Composable
fun MovieDetailsRoute(
    navController: NavController,
    movieDetailsViewModel: MovieDetailsViewModel,
    movieId: Int
) {
    Tmdb_TestTheme {
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