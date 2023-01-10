package com.tmdb_test.store.reducer

import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.Reducer
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.reducer.details.MovieDetailsFeatureSlice
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import com.tmdb_test.store.state.AppState

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