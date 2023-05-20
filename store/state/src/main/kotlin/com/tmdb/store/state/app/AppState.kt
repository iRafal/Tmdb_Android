package com.tmdb.store.state.app

import com.tmdb.store.state.details.MovieDetailsFeatureState
import com.tmdb.store.state.home.HomeFeatureState

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
