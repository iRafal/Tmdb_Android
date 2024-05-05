package com.tmdb.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.di.HomeFeatureDi
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute
import com.tmdb.ui.core.di.daggerViewModel

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val component = remember { HomeFeatureDi.fromContext(context) }
    val homeViewModel: HomeViewModel = daggerViewModel { component.homeViewModel }

    val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when (event) {
            HomeUiEvent.NavigateBack -> navController.navigate(NavigationRoute.Close.route)
            is HomeUiEvent.OpenMovie -> {
                navController.navigate(
                    NavigationRoute.MovieDetails.getRouteNameWithArguments(event.id.toString())
                )
            }
            is HomeUiEvent.ReloadMovieSection -> homeViewModel.onReloadMovieSection(event.movieSection)
        }
    }
    HomeScreenUi(data, onEvent)
}
