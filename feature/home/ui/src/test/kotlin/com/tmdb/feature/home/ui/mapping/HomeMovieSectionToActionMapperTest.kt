package com.tmdb.feature.home.ui.mapping

import com.tmdb.feature.home.ui.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieSectionToActionMapperImpl
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.store.action.HomeAction
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeMovieSectionToActionMapperTest {
    private val mapper: HomeMovieSectionToActionMapper = HomeMovieSectionToActionMapperImpl()

    @Test
    fun `test map Now Playing section to Action`() {
        val section = NOW_PLAYING
        val actual = mapper.map(section)

        assertEquals(expected = HomeAction.ReloadNowPlayingMovies, actual = actual)
    }

    @Test
    fun `test map Now Popular section to Action`() {
        val section = NOW_POPULAR
        val actual = mapper.map(section)

        assertEquals(expected = HomeAction.ReloadNowPopularMovies, actual = actual)
    }

    @Test
    fun `test map Top Rated section to Action`() {
        val section = TOP_RATED
        val actual = mapper.map(section)

        assertEquals(expected = HomeAction.ReloadTopRatedMovies, actual = actual)
    }

    @Test
    fun `test map Upcoming section to Action`() {
        val section = UPCOMING
        val actual = mapper.map(section)

        assertEquals(expected = HomeAction.ReloadUpcomingMovies, actual = actual)
    }
}
