package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType
import com.tmdb.store.action.HomeAction

fun interface HomeMovieGroupToActionMapper {
    fun map(input: HomeMovieSectionType): HomeAction
}

class HomeMovieGroupToActionMapperImpl : HomeMovieGroupToActionMapper {
    override fun map(input: HomeMovieSectionType): HomeAction {
        return when (input) {
            HomeMovieSectionType.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
            HomeMovieSectionType.POPULAR -> HomeAction.ReloadPopularMovies
            HomeMovieSectionType.TOP_RATED -> HomeAction.ReloadTopRatedMovies
            HomeMovieSectionType.UPCOMING -> HomeAction.ReloadUpcomingMovies
        }
    }
}
