package com.tmdb_test.ui.home.data.mapping

import com.tmdb_test.store.action.home.HomeAction
import com.tmdb_test.ui.home.data.HomeMovieSection
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeMovieSectionToActionMapperTest {
    private val mapper: HomeMovieSectionToActionMapper = ::homeMovieSectionToActionMapperImpl

    @Test
    fun `test map Now Playing section to Action`() {
        val section = HomeMovieSection.NOW_PLAYING
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadNowPlayingMovies
        assertEquals(expected, actual)
    }

    @Test
    fun `test map Now Popular section to Action`() {
        val section = HomeMovieSection.NOW_POPULAR
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadNowPopularMovies
        assertEquals(expected, actual)
    }

    @Test
    fun `test map Top Rated section to Action`() {
        val section = HomeMovieSection.TOP_RATED
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadTopRatedMovies
        assertEquals(expected, actual)
    }

    @Test
    fun `test map Upcoming section to Action`() {
        val section = HomeMovieSection.UPCOMING
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadUpcomingMovies
        assertEquals(expected, actual)
    }
}