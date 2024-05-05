package com.tmdb.ui.gallery.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tmdb.ui.gallery.app.navigation.AppNavigationRoute.Home
import com.tmdb.ui.gallery.home.HomeScreen

@Composable
fun AppNavigation(navHostController: NavHostController = rememberNavController()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navHostController,
            startDestination = Home.route
        ) {
            appNavigationGraph(navHostController)
        }
    }
}

@Suppress("LongMethod")
fun NavGraphBuilder.appNavigationGraph(navController: NavController) {
    composable(Home.route) {
        HomeScreen(
            navigateBack = { navController.popBackStack() },
        )
    }
}
