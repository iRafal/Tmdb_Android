package com.tmdb_test.feature.home.data.mapping

import com.tmdb_test.feature.home.content.HomeMovieSection
import com.tmdb_test.feature.home.store.HomeAction

typealias HomeMovieSectionToActionMapper = (input: HomeMovieSection) -> HomeAction

fun homeMovieSectionToActionMapperImpl(input: HomeMovieSection): HomeAction {
    return when (input) {
        HomeMovieSection.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
        HomeMovieSection.NOW_POPULAR -> HomeAction.ReloadNowPopularMovies
        HomeMovieSection.TOP_RATED -> HomeAction.ReloadTopRatedMovies
        HomeMovieSection.UPCOMING -> HomeAction.ReloadUpcomingMovies
    }
}