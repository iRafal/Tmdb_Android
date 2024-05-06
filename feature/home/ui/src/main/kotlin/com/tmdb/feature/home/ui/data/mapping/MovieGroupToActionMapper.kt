package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType
import com.tmdb.store.action.HomeAction
import javax.inject.Inject

interface HomeMovieGroupToActionMapper {
    fun map(input: HomeMovieSectionType): HomeAction
}

class HomeMovieGroupToActionMapperImpl @Inject constructor() : HomeMovieGroupToActionMapper {
    override fun map(input: HomeMovieSectionType): HomeAction {
        return when (input) {
            HomeMovieSectionType.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
            HomeMovieSectionType.POPULAR -> HomeAction.ReloadPopularMovies
            HomeMovieSectionType.TOP_RATED -> HomeAction.ReloadTopRatedMovies
            HomeMovieSectionType.UPCOMING -> HomeAction.ReloadUpcomingMovies
        }
    }
}
