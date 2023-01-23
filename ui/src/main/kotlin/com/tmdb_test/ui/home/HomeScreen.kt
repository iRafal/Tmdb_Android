package com.tmdb_test.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb_test.ui.home.HomeUiEvent.NavigateBack
import com.tmdb_test.ui.home.HomeUiEvent.OpenMovie
import com.tmdb_test.ui.home.HomeUiEvent.ReloadMovieSection
import com.tmdb_test.ui.home.data.HomeUiData
import com.tmdb_test.ui.navigation.AppNavigation
import com.tmdb_test.ui.navigation.AppNavigation.Close
import com.tmdb_test.ui.theme.Tmdb_TestTheme

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    Tmdb_TestTheme {
        val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

        val onEvent: (HomeUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> navController.navigate(Close.route)
                is OpenMovie -> {
                    navController.navigate(
                        AppNavigation.MovieDetails.getRouteNameWithArguments(
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