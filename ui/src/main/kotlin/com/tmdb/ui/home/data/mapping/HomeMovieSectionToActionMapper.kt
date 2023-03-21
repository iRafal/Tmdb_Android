package com.tmdb.ui.home.data.mapping

import com.tmdb.store.action.home.HomeAction
import com.tmdb.ui.home.data.HomeMovieSection

typealias HomeMovieSectionToActionMapper = (input: HomeMovieSection) -> HomeAction

fun homeMovieSectionToActionMapperImpl(input: HomeMovieSection): HomeAction {
    return when (input) {
        HomeMovieSection.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
        HomeMovieSection.NOW_POPULAR -> HomeAction.ReloadNowPopularMovies
        HomeMovieSection.TOP_RATED -> HomeAction.ReloadTopRatedMovies
        HomeMovieSection.UPCOMING -> HomeAction.ReloadUpcomingMovies
    }
}