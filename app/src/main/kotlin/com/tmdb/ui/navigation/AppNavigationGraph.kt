package com.tmdb.ui.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.feature.home.ui.HomeScreen
import com.tmdb.feature.movie.details.ui.MovieDetailsScreen
import com.tmdb.feature.movie.details.ui.MovieDetailsViewModel
import com.tmdb.feature.movie.details.ui.di.MovieDetailsFeatureDi
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute
import com.tmdb.ui.core.di.daggerAssistedViewModel

fun NavGraphBuilder.appNavigationGraph(navController: NavController) {
    composable<NavigationRoute.Home> {
        HomeScreen(navController)
    }
    composable<NavigationRoute.MovieDetails> {
        val context = LocalContext.current
        val component = remember { MovieDetailsFeatureDi.fromContext(context) }
        val movieDetailsViewModel: MovieDetailsViewModel = daggerAssistedViewModel(
            assistedViewModelCreator = component.movieDetailsViewModelCreator,
            defaultArgs = it.arguments,
        )
        MovieDetailsScreen(navController, movieDetailsViewModel)
    }
}
