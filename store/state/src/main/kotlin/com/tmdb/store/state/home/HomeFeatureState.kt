package com.tmdb.store.state.home

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.store.state.FeatureState

data class HomeFeatureState(
    val nowPlayingMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading(),
    val nowPopularMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading(),
    val topRatedMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading(),
    val upcomingMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading()
) {
    companion object {
        val INITIAL = HomeFeatureState()
    }
}
