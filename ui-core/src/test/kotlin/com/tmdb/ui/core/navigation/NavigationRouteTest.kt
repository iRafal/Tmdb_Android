package com.tmdb.ui.core.navigation

import com.tmdb.ui.core.compose.navigation.model.NavigationRoute
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals


class NavigationRouteTest {
    @Test
    fun `home navigation route`() {
        assertEquals(expected = NavigationRoute.ROUTE_HOME, actual = "home")
        assertEquals(expected = NavigationRoute.Home.route, actual = NavigationRoute.ROUTE_HOME)
    }

    @Test
    fun `movie details navigation route`() {
        assertEquals(expected = NavigationRoute.ROUTE_MOVIE_DETAILS, actual = "movie/details?movieId={movieId}")
        assertEquals(expected = NavigationRoute.MovieDetails.route, actual = NavigationRoute.ROUTE_MOVIE_DETAILS)
        assertEquals(expected = NavigationRoute.MovieDetails.ARG_MOVIE_ID, actual = "movieId")

        val id = UUID.randomUUID().toString()
        assertEquals(
            expected = NavigationRoute.MovieDetails.getRouteNameWithArguments(id),
            actual = "movie/details?movieId=$id"
        )
    }

    @Test
    fun `close navigation route`() {
        assertEquals(expected = NavigationRoute.ROUTE_CLOSE, actual = "close")
        assertEquals(expected = NavigationRoute.Close.route, actual = NavigationRoute.ROUTE_CLOSE)
    }
}
