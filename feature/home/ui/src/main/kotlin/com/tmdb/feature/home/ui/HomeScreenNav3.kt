package com.tmdb.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.tmdb.feature.home.ui.HomeUiEvent.OpenMovie
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.di.HomeFeatureDi
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute.MovieDetails
import com.tmdb.ui.core.di.daggerViewModel

@Composable
fun HomeScreenNav3(movieDetails: (MovieDetails) -> Unit, back: () -> Unit) {
    val context = LocalContext.current
    val component = remember { HomeFeatureDi.fromContext(context) }
    val viewModel: HomeViewModel = daggerViewModel { component.homeViewModel }
    val data by viewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when (event) {
            HomeUiEvent.NavigateBack -> back()
            is OpenMovie -> movieDetails(MovieDetails(event.id.toString()))
            is HomeUiEvent.ReloadMovieSection -> viewModel.reloadMovieGroup(event.movieSection)
            HomeUiEvent.ReloadAll -> viewModel.reloadAllMovies()
        }
    }
    HomeScreenUi(data, onEvent)
}
