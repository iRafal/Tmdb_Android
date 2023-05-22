package com.tmdb.ui.core.navigation

import com.tmdb.ui.core.navigation.model.NavigationRoute
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationRouteTest {
    @Test
    fun `home navigation route`() {
        assertEquals(NavigationRoute.ROUTE_HOME, "home")
        assertEquals(NavigationRoute.Home.route, NavigationRoute.ROUTE_HOME)
    }

    @Test
    fun `movie details navigation route`() {
        assertEquals(NavigationRoute.ROUTE_MOVIE_DETAILS, "movie/details?movieId={movieId}")
        assertEquals(NavigationRoute.MovieDetails.route, NavigationRoute.ROUTE_MOVIE_DETAILS)
        assertEquals(NavigationRoute.MovieDetails.ARG_MOVIE_ID, "movieId")

        val id = UUID.randomUUID().toString()
        assertEquals(
            NavigationRoute.MovieDetails.getRouteNameWithArguments(id),
            "movie/details?movieId=$id"
        )
    }

    @Test
    fun `close navigation route`() {
        assertEquals(NavigationRoute.ROUTE_CLOSE, "close")
        assertEquals(NavigationRoute.Close.route, NavigationRoute.ROUTE_CLOSE)
    }
}
