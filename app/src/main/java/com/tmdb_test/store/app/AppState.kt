package com.tmdb_test.store.app

import com.tmdb_test.feature.home.store.HomeFeatureState
import com.tmdb_test.feature.movie.details.store.MovieDetailsFeatureState

data class AppState(
    val homeState: HomeFeatureState,
    val movieDetailsState: MovieDetailsFeatureState
) {
    companion object {
        val INITIAL = AppState(
            homeState = HomeFeatureState.INITIAL,
            movieDetailsState = MovieDetailsFeatureState.INITIAL
        )
    }
}