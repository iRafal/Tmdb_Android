package com.tmdb_test.feature.home.store

import com.tmdb_test.data.model.MovieDataModel
import com.tmdb_test.store.FeatureState


data class HomeFeatureState(
    val nowPlayingMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading(),
    val nowPopularMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading(),
    val topRatedMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading(),
    val upcomingMoviesState: FeatureState<List<MovieDataModel>> = FeatureState.Loading(),
) {
    companion object {
        val INITIAL = HomeFeatureState()
    }
}