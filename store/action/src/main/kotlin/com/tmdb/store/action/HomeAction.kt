package com.tmdb.store.action

import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.store.base.Action

sealed interface HomeAction : Action {
    data object LoadMovieSections : HomeAction
    data class MovieSectionsLoaded(
        val nowPlayingMovies: DataState<List<MovieDataModel>>,
        val popularMovies: DataState<List<MovieDataModel>>,
        val topRatedMovies: DataState<List<MovieDataModel>>,
        val upcomingMovies: DataState<List<MovieDataModel>>
    ) : HomeAction

    data object ReloadNowPlayingMovies : HomeAction
    data object ReloadPopularMovies : HomeAction
    data object ReloadTopRatedMovies : HomeAction
    data object ReloadUpcomingMovies : HomeAction
    data class NowPlayingMoviesLoaded(val nowPlayingMovies: DataState<List<MovieDataModel>>) : HomeAction
    data class PopularMoviesLoaded(val popularMovies: DataState<List<MovieDataModel>>) : HomeAction
    data class TopRatedMoviesLoaded(val topRatedMovies: DataState<List<MovieDataModel>>) : HomeAction
    data class UpcomingMoviesLoaded(val upcomingMovies: DataState<List<MovieDataModel>>) : HomeAction
    data object ReloadAllMovies : HomeAction
    data object ResetState : HomeAction
}
