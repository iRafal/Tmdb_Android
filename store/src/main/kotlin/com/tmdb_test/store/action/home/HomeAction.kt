package com.tmdb_test.store.action.home

import com.tmdb_test.data.source.model.DataState
import com.tmdb_test.data.source.model.MovieDataModel
import com.tmdb_test.store.base.Action

sealed interface HomeAction : Action {
    object LoadMovieSections : HomeAction
    data class MovieSectionsLoaded(
        val nowPlayingMovies: DataState<List<MovieDataModel>>,
        val nowPopularMovies: DataState<List<MovieDataModel>>,
        val topRatedMovies: DataState<List<MovieDataModel>>,
        val upcomingMovies: DataState<List<MovieDataModel>>,
    ) : HomeAction

    object ReloadNowPlayingMovies : HomeAction
    object ReloadNowPopularMovies : HomeAction
    object ReloadTopRatedMovies : HomeAction
    object ReloadUpcomingMovies : HomeAction
}