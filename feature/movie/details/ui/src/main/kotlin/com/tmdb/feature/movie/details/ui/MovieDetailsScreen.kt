package com.tmdb.feature.movie.details.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tmdb.feature.movie.details.ui.MovieDetailsUiEvent.NavigateBack
import com.tmdb.feature.movie.details.ui.MovieDetailsUiState.Loading
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
) {
    val movieDetailsViewModel: MovieDetailsViewModel = viewModel()
    val state = Loading
//        val state by movieDetailsViewModel.state.collectAsState(MovieDetailsState.Idle)
    val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
        when (event) {
            NavigateBack -> navController.navigate(NavigationRoute.Close.route)
        }
    }
    MovieDetailsScreenUi(state, onEvent)
}
