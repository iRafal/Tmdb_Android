package com.tmdb_test.feature.home.store

import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.store.base.Action
import com.tmdb_test.data.util.DataState

sealed interface HomeAction : Action {
    object LoadMovieSections : HomeAction
    data class MovieSectionsLoaded(
        val nowPlayingMovies: DataState<List<Movie>>,
        val nowPopularMovies: DataState<List<Movie>>,
        val topRatedMovies: DataState<List<Movie>>,
        val upcomingMovies: DataState<List<Movie>>,
    ) : HomeAction

    object ReloadNowPlayingMovies : HomeAction
    object ReloadNowPopularMovies : HomeAction
    object ReloadTopRatedMovies : HomeAction
    object ReloadUpcomingMovies : HomeAction
}