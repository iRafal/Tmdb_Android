package com.tmdb_test.ui.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb_test.ui.app.navigation.AppNavigation.Close
import com.tmdb_test.ui.core.theme.Tmdb_TestTheme
import com.tmdb_test.ui.details.MovieDetailsUiEvent.NavigateBack
import com.tmdb_test.ui.details.MovieDetailsUiState.Loading

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    Tmdb_TestTheme {
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