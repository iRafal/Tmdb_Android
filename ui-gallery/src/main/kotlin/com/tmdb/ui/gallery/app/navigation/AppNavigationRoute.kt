package com.tmdb.ui.gallery.app.navigation

sealed class AppNavigationRoute(val route: String) {
    data object Home : AppNavigationRoute(route = ROUTE_HOME)
    companion object {
        private const val ROUTE_HOME = "home"
    }
}
