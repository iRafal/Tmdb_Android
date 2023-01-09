package com.tmdb_test.store.app

import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.Reducer
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import com.tmdb_test.store.reducer.movie.details.MovieDetailsFeatureSlice

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