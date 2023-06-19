package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.action.HomeAction
import com.tmdb.feature.home.ui.data.model.HomeMovieSection

typealias HomeMovieSectionToActionMapper = (input: HomeMovieSection) -> HomeAction

fun homeMovieSectionToActionMapperImpl(input: HomeMovieSection): HomeAction {
    return when (input) {
        HomeMovieSection.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
        HomeMovieSection.NOW_POPULAR -> HomeAction.ReloadNowPopularMovies
        HomeMovieSection.TOP_RATED -> HomeAction.ReloadTopRatedMovies
        HomeMovieSection.UPCOMING -> HomeAction.ReloadUpcomingMovies
    }
}
