package com.tmdb_test.store.state

import androidx.annotation.VisibleForTesting
import com.tmdb_test.store.state.home.HomeFeatureState
import com.tmdb_test.store.state.details.MovieDetailsFeatureState


data class AppState(
    val homeState: HomeFeatureState,
    val movieDetailsState: MovieDetailsFeatureState
) {
    companion object {
        fun createInitialState(
            homeState: HomeFeatureState,
            movieDetailsState: MovieDetailsFeatureState
        ) = AppState(
            homeState,
            movieDetailsState
        )

        @VisibleForTesting
        val INITIAL = AppState(
            homeState = HomeFeatureState.INITIAL,
            movieDetailsState = MovieDetailsFeatureState.INITIAL
        )
    }
}