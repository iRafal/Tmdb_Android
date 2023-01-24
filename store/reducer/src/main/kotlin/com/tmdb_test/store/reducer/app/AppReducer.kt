package com.tmdb_test.store.reducer.app

import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.Reducer
import com.tmdb_test.store.env.contract.AppEnv
import com.tmdb_test.store.reducer.details.MovieDetailsFeatureSlice
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import com.tmdb_test.store.state.app.AppState

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