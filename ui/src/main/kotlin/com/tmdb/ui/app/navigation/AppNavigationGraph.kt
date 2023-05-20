package com.tmdb.ui.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.feature.home.ui.HomeScreen
import com.tmdb.feature.movie.details.ui.MovieDetailsScreen
import com.tmdb.ui.core.navigation.app.model.AppNavigation.Close
import com.tmdb.ui.core.navigation.app.model.AppNavigation.Home
import com.tmdb.ui.core.navigation.app.model.AppNavigation.MovieDetails


fun NavGraphBuilder.appNavigationGraph(
    navController: NavController,
    onClose: () -> Unit,
) {
    composable(Home.route) {
        HomeScreen(navController)
    }
    composable(MovieDetails.route) {
        val movieId = checkNotNull(it.arguments?.getString(MovieDetails.ARG_MOVIE_ID)?.toInt())
        MovieDetailsScreen(navController, movieId)
    }
    composable(Close.route) {
        onClose()
    }
}