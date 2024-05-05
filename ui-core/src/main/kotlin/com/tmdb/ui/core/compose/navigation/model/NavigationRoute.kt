package com.tmdb.ui.core.compose.navigation.model

import androidx.annotation.VisibleForTesting

sealed class NavigationRoute(val route: String) {
    data object Home : NavigationRoute(ROUTE_HOME)
    data object MovieDetails : NavigationRoute(ROUTE_MOVIE_DETAILS) {
        const val ARG_MOVIE_ID = "movieId"
        fun getRouteNameWithArguments(movieId: String): String = "movie/details?movieId=$movieId"
    }

    data object Close : NavigationRoute(ROUTE_CLOSE)

    companion object {
        @VisibleForTesting
        internal const val ROUTE_HOME = "home"

        @VisibleForTesting
        internal const val ROUTE_MOVIE_DETAILS = "movie/details?movieId={movieId}"

        @VisibleForTesting
        internal const val ROUTE_CLOSE = "close"
    }
}
