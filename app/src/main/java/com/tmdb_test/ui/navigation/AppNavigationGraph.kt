package com.tmdb_test.ui.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb_test.MainViewModel
import com.tmdb_test.ui.navigation.AppNavigation.Close
import com.tmdb_test.ui.navigation.AppNavigation.Home
import com.tmdb_test.ui.navigation.AppNavigation.MovieDetails
import com.tmdb_test.ui.home.HomeScreen
import com.tmdb_test.ui.movie.details.MovieDetailsRoute
import com.tmdb_test.ui.home.HomeViewModel
import com.tmdb_test.ui.movie.details.MovieDetailsViewModel


fun NavGraphBuilder.mainNavigationGraph(
    navController: NavController,
    homeViewModel: HomeViewModel,
    movieDetailsViewModel: MovieDetailsViewModel,
    mainViewModel: MainViewModel,
    onClose: () -> Unit,
) {
    composable(Home.route) {
        HomeScreen(navController, homeViewModel, mainViewModel)
    }
    composable(MovieDetails.route) {
        val movieId = checkNotNull(it.arguments?.getString(MovieDetails.ARG_MOVIE_ID)?.toInt())
        MovieDetailsRoute(navController, movieDetailsViewModel, movieId)
    }
    composable(Close.route) {
        onClose()
    }
}