package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.ui.data.model.HomeMovieSection
import com.tmdb.store.action.HomeAction
import javax.inject.Inject

interface HomeMovieSectionToActionMapper {
    fun map(input: HomeMovieSection): HomeAction
}

class HomeMovieSectionToActionMapperImpl @Inject constructor() : HomeMovieSectionToActionMapper {
    override fun map(input: HomeMovieSection): HomeAction {
        return when (input) {
            HomeMovieSection.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
            HomeMovieSection.NOW_POPULAR -> HomeAction.ReloadNowPopularMovies
            HomeMovieSection.TOP_RATED -> HomeAction.ReloadTopRatedMovies
            HomeMovieSection.UPCOMING -> HomeAction.ReloadUpcomingMovies
        }
    }
}
