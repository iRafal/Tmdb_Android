package com.tmdb_test.ui.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb_test.MainViewModel
import com.tmdb_test.ui.app.navigation.AppNavigation.Close
import com.tmdb_test.ui.app.navigation.AppNavigation.Home
import com.tmdb_test.ui.app.navigation.AppNavigation.MovieDetails
import com.tmdb_test.feature.home.content.HomeRoute
import com.tmdb_test.feature.home.content.HomeViewModel
import com.tmdb_test.feature.movie.details.content.MovieDetailsRoute
import com.tmdb_test.feature.movie.details.content.MovieDetailsViewModel


fun NavGraphBuilder.mainNavigationGraph(
    navController: NavController,
    homeViewModel: HomeViewModel,
    movieDetailsViewModel: MovieDetailsViewModel,
    mainViewModel: MainViewModel,
    onClose: () -> Unit,
) {
    composable(Home.route) {
        HomeRoute(navController, homeViewModel, mainViewModel)
    }
    composable(MovieDetails.route) {
        val movieId = checkNotNull(it.arguments?.getString(MovieDetails.ARG_MOVIE_ID)?.toInt())
        MovieDetailsRoute(navController, movieDetailsViewModel, movieId)
    }
    composable(Close.route) {
        onClose()
    }
}