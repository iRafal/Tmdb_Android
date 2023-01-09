package com.tmdb_test.ui.navigation

sealed class AppNavigation(val route: String) {
    object Home : AppNavigation("home")
    object MovieDetails : AppNavigation("movie/details?movieId={movieId}") {
        const val ARG_MOVIE_ID = "movieId"
        fun getRouteNameWithArguments(movieId: String): String = "movie/details?movieId=$movieId"
    }

    object Close : AppNavigation("close")
}