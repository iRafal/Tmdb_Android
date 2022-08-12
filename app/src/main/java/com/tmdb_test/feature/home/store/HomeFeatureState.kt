package com.tmdb_test.feature.home.store

import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.store.FeatureState


data class HomeFeatureState(
    val nowPlayingMoviesState: FeatureState<List<Movie>> = FeatureState.Loading(),
    val nowPopularMoviesState: FeatureState<List<Movie>> = FeatureState.Loading(),
    val topRatedMoviesState: FeatureState<List<Movie>> = FeatureState.Loading(),
    val upcomingMoviesState: FeatureState<List<Movie>> = FeatureState.Loading(),
) {
    companion object {
        val INITIAL = HomeFeatureState()
    }
}