package com.tmdb.store.action.home

import com.tmdb.data.model.state.DataState
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.store.base.Action

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