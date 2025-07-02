package com.tmdb.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.feature.home.ui.HomeScreen
import com.tmdb.feature.movie.details.ui.MovieDetailsScreen
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute

fun NavGraphBuilder.appNavigationGraph(navController: NavController) {
    composable<NavigationRoute.Home> {
        HomeScreen(navController)
    }
    composable<NavigationRoute.MovieDetails> {
        MovieDetailsScreen(navController)
    }
}
