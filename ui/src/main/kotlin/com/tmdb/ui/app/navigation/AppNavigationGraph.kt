package com.tmdb.ui.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.ui.details.MovieDetailsScreen
import com.tmdb.ui.home.HomeScreen
import com.tmdb.ui.app.navigation.AppNavigation.Close
import com.tmdb.ui.app.navigation.AppNavigation.Home
import com.tmdb.ui.app.navigation.AppNavigation.MovieDetails


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