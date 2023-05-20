package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.store.action.home.HomeAction
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeMovieSectionToActionMapperTest {
    private val mapper: HomeMovieSectionToActionMapper = ::homeMovieSectionToActionMapperImpl

    @Test
    fun `test map Now Playing section to Action`() {
        val section = NOW_PLAYING
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadNowPlayingMovies
        assertEquals(expected, actual)
    }

    @Test
    fun `test map Now Popular section to Action`() {
        val section = NOW_POPULAR
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadNowPopularMovies
        assertEquals(expected, actual)
    }

    @Test
    fun `test map Top Rated section to Action`() {
        val section = TOP_RATED
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadTopRatedMovies
        assertEquals(expected, actual)
    }

    @Test
    fun `test map Upcoming section to Action`() {
        val section = UPCOMING
        val actual = mapper.invoke(section)
        val expected = HomeAction.ReloadUpcomingMovies
        assertEquals(expected, actual)
    }
}
