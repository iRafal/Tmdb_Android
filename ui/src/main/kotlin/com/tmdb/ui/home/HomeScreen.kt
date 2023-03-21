package com.tmdb.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb.ui.home.HomeUiEvent.NavigateBack
import com.tmdb.ui.home.HomeUiEvent.OpenMovie
import com.tmdb.ui.home.HomeUiEvent.ReloadMovieSection
import com.tmdb.ui.home.data.HomeUiData
import com.tmdb.ui.app.navigation.AppNavigation.Close
import com.tmdb.ui.app.navigation.AppNavigation.MovieDetails
import com.tmdb.ui.core.theme.TmdbTheme

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    TmdbTheme {
        val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

        val onEvent: (HomeUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> navController.navigate(Close.route)
                is OpenMovie -> {
                    navController.navigate(
                        MovieDetails.getRouteNameWithArguments(
                            event.id.toString()
                        )
                    )
                }
                is ReloadMovieSection -> homeViewModel.onReloadMovieSection(event.movieSection)
            }
        }
        HomeScreenUi(data, onEvent)
    }
}