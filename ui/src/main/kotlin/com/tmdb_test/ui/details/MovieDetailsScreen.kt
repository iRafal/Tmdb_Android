package com.tmdb_test.ui.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tmdb_test.ui.navigation.AppNavigation
import com.tmdb_test.ui.theme.Tmdb_TestTheme

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieDetailsViewModel: MovieDetailsViewModel,
    movieId: Int
) {
    Tmdb_TestTheme {
        val state = MovieDetailsUiState.Loading
//        val state by movieDetailsViewModel.state.collectAsState(MovieDetailsState.Idle)
        val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
            when (event) {
                MovieDetailsUiEvent.NavigateBack -> navController.navigate(AppNavigation.Close.route)
            }
        }
        MovieDetailsScreenUi(state, onEvent)
    }
}