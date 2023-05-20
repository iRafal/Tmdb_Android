package com.tmdb.ui.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tmdb.ui.core.navigation.app.model.AppNavigation.Home

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController(), onClose: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        this.appNavigationGraph(navController, onClose)
    }
}
