package com.tmdb_test.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb_test.ui.details.MovieDetailsScreen
import com.tmdb_test.ui.details.MovieDetailsViewModel
import com.tmdb_test.ui.home.HomeScreen
import com.tmdb_test.ui.home.HomeViewModel
import com.tmdb_test.ui.navigation.AppNavigation.Close
import com.tmdb_test.ui.navigation.AppNavigation.Home
import com.tmdb_test.ui.navigation.AppNavigation.MovieDetails


fun NavGraphBuilder.mainNavigationGraph(
    navController: NavController,
    homeViewModel: HomeViewModel,
    movieDetailsViewModel: MovieDetailsViewModel,
    onClose: () -> Unit,
) {
    composable(Home.route) {
        HomeScreen(navController, homeViewModel)
    }
    composable(MovieDetails.route) {
        val movieId = checkNotNull(it.arguments?.getString(MovieDetails.ARG_MOVIE_ID)?.toInt())
        MovieDetailsScreen(navController, movieDetailsViewModel, movieId)
    }
    composable(Close.route) {
        onClose()
    }
}