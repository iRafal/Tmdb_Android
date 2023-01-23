package com.tmdb_test.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb_test.ui.details.MovieDetailsScreen
import com.tmdb_test.ui.home.HomeScreen
import com.tmdb_test.ui.navigation.AppNavigation.Close
import com.tmdb_test.ui.navigation.AppNavigation.Home
import com.tmdb_test.ui.navigation.AppNavigation.MovieDetails


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