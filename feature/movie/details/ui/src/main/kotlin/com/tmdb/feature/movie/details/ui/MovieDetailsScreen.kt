package com.tmdb.feature.movie.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.tmdb.feature.movie.details.ui.MovieDetailsUiEvent.NavigateBack
import com.tmdb.feature.movie.details.ui.MovieDetailsUiState.Loading
import com.tmdb.feature.movie.details.ui.di.MovieDetailsFeatureDi
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute
import com.tmdb.ui.core.di.daggerViewModel

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
) {
    val context = LocalContext.current
    val component = remember { MovieDetailsFeatureDi.fromContext(context) }
    val movieDetailsViewModel: MovieDetailsViewModel = daggerViewModel { component.movieDetailsViewModel }
    val state = Loading
//        val state by movieDetailsViewModel.state.collectAsState(MovieDetailsState.Idle)
    val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
        when (event) {
            NavigateBack -> navController.navigate(NavigationRoute.Close.route)
        }
    }
    MovieDetailsScreenUi(state, onEvent)
}
