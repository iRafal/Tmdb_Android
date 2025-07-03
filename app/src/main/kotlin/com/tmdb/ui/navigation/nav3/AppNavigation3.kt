@file:JvmName("AppNavEntryKt")

package com.tmdb.ui.navigation.nav3

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.tmdb.feature.home.ui.HomeScreenNav3
import com.tmdb.feature.movie.details.ui.MovieDetailsScreenNav3
import com.tmdb.ui.core.compose.navigation.model.NavigationRouteNew

@Composable
fun AppNavigation3() {
    val backStack = rememberNavBackStack(NavigationRouteNew.Home)
    NavDisplay(
        backStack,
        entryProvider = entryProvider {
            val back: () -> Unit = {
                backStack.removeAt(backStack.lastIndex)
                //backStack.removeLast() //min api 26 required
            }
            entry<NavigationRouteNew.Home> {
                HomeScreenNav3(
                    movieDetails = { backStack.add(NavigationRouteNew.MovieDetails(it.movieId)) },
                    back = back
                )
            }
            entry<NavigationRouteNew.MovieDetails> {
                MovieDetailsScreenNav3(back = back)
            }
        }
    )
}
