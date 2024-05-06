package com.tmdb.feature.home.ui.mapping

import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapperImpl
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.UPCOMING
import com.tmdb.store.action.HomeAction
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieGroupTypeToActionMapperTest {
    private val mapper: HomeMovieGroupToActionMapper = HomeMovieGroupToActionMapperImpl()

    @Test
    fun `test map Now Playing section to Action`() {
        val section = NOW_PLAYING
        val actual = mapper.map(section)

        assertEquals(expected = HomeAction.ReloadNowPlayingMovies, actual = actual)
    }

    @Test
    fun `test map Now Popular section to Action`() {
        val section = POPULAR
        val actual = mapper.map(section)

        assertEquals(expected = HomeAction.ReloadPopularMovies, actual = actual)
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
