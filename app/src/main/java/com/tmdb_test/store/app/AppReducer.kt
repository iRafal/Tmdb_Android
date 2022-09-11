package com.tmdb_test.store.app

import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.feature.movie.details.store.MovieDetailsFeatureSlice
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.Reducer
import com.tmdb_test.store.env.AppEnv

typealias AppReducer = Reducer<AppState, AppEnv>

fun createAppReducer(homeFeatureSlice: HomeFeatureSlice): AppReducer {
    return { state, action ->
        val (homeState, homeEffect) = homeFeatureSlice.reducer(state, action)
        val (movieDetailsState, movieDetailsEffect) = MovieDetailsFeatureSlice.reducer(
            state,
            action
        )

        val newGlobalState =
            state.copy(homeState = homeState, movieDetailsState = movieDetailsState)
        newGlobalState to Effects.merge(homeEffect, movieDetailsEffect)
    }
}