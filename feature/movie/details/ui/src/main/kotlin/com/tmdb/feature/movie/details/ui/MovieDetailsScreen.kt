package com.tmdb.feature.movie.details.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.tmdb.feature.movie.details.ui.MovieDetailsUiEvent.NavigateBack
import com.tmdb.feature.movie.details.ui.MovieDetailsUiState.Loading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    viewModel: MovieDetailsViewModel = koinViewModel(),
) {
    val state = Loading
//        val state by viewModel.state.collectAsState(MovieDetailsState.Idle)
    val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
        when (event) {
            NavigateBack -> navController.popBackStack()
        }
    }
    MovieDetailsScreenUi(state, onEvent)
}
