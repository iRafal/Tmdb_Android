package com.tmdb.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.tmdb.feature.home.ui.HomeUiEvent.OpenMovie
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.di.HomeFeatureDi
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute.Close
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute.MovieDetails
import com.tmdb.ui.core.di.daggerViewModel

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val component = remember { HomeFeatureDi.fromContext(context) }
    val viewModel: HomeViewModel = daggerViewModel { component.homeViewModel }

    val data by viewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when (event) {
            HomeUiEvent.NavigateBack -> navController.navigate(Close.route)
            is OpenMovie -> {
                navController.navigate(
                    MovieDetails.getRouteNameWithArguments(event.id.toString())
                )
            }
            is HomeUiEvent.ReloadMovieSection -> viewModel.onReloadMovieSection(event.movieSection)
            HomeUiEvent.ReloadAll -> viewModel.onReloadAll()
        }
    }
    HomeScreenUi(data, onEvent)
}
