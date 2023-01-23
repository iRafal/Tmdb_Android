package com.tmdb_test.ui.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tmdb_test.ui.app.navigation.AppNavigation.Home

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController(), onClose: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        this.appNavigationGraph(navController, onClose)
    }
}