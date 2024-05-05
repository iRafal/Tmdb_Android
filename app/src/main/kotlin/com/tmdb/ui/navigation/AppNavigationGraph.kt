package com.tmdb.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.feature.home.ui.HomeScreen
import com.tmdb.feature.movie.details.ui.MovieDetailsScreen
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute

fun NavGraphBuilder.appNavigationGraph(
    navController: NavController,
    onClose: () -> Unit
) {
    composable(NavigationRoute.Home.route) {
        HomeScreen(navController)
    }
    composable(NavigationRoute.MovieDetails.route) {
        val movieId = checkNotNull(it.arguments?.getString(NavigationRoute.MovieDetails.ARG_MOVIE_ID)?.toInt())
        MovieDetailsScreen(navController, movieId)
    }
    composable(NavigationRoute.Close.route) {
        onClose()
    }
}
