package com.tmdb.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb.feature.home.ui.HomeUiEvent.NavigateBack
import com.tmdb.feature.home.ui.HomeUiEvent.OpenMovie
import com.tmdb.feature.home.ui.HomeUiEvent.ReloadMovieSection
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.ui.core.navigation.model.NavigationRoute
import com.tmdb.ui.core.theme.TmdbTheme

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    TmdbTheme {
        val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

        val onEvent: (HomeUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> navController.navigate(NavigationRoute.Close.route)
                is OpenMovie -> {
                    navController.navigate(
                        NavigationRoute.MovieDetails.getRouteNameWithArguments(
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
