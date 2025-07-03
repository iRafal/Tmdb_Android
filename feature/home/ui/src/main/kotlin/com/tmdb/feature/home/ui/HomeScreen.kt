package com.tmdb.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.tmdb.feature.home.ui.HomeUiEvent.OpenMovie
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute.MovieDetails
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()) {
    val data by viewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when (event) {
            HomeUiEvent.NavigateBack -> navController.popBackStack()
            is OpenMovie -> navController.navigate(MovieDetails(event.id.toString()))
            is HomeUiEvent.ReloadMovieSection -> viewModel.reloadMovieGroup(event.movieSection)
            HomeUiEvent.ReloadAll -> viewModel.reloadAllMovies()
        }
    }
    HomeScreenUi(data, onEvent)
}
