package com.tmdb_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tmdb_test.ui.details.MovieDetailsViewModel
import com.tmdb_test.ui.home.HomeViewModel
import com.tmdb_test.ui.main.MainViewModel
import com.tmdb_test.ui.navigation.AppNavigation
import com.tmdb_test.ui.navigation.mainNavigationGraph
import com.tmdb_test.ui.theme.Tmdb_TestTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    private val movieDetailsViewModel by viewModels<MovieDetailsViewModel>()

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SetContent() }
        observeData()
    }

    @Composable
    private fun SetContent() {
        Tmdb_TestTheme { MainNavigation() }
    }

    @Composable
    fun MainNavigation(navController: NavHostController = rememberNavController()) {
        NavHost(
            navController = navController,
            startDestination = AppNavigation.Home.route
        ) {
            this.mainNavigationGraph(
                navController,
                homeViewModel,
                movieDetailsViewModel,
                onClose = { finish() }
            )
        }
    }

    private fun observeData() {

    }
}