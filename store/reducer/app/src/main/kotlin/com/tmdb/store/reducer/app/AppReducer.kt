package com.tmdb.store.reducer.app

import com.tmdb.feature.reducer.HomeFeatureSlice
import com.tmdb.store.base.Effects
import com.tmdb.store.base.Reducer
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.reducer.movie.details.MovieDetailsFeatureSlice
import com.tmdb.store.state.app.AppState

typealias AppReducer = Reducer<AppState, AppEnv>

fun createAppReducer(
    homeFeatureSlice: HomeFeatureSlice,
    movieDetailsFeatureSlice: MovieDetailsFeatureSlice
): AppReducer {
    return { state, action ->
        val (homeState, homeEffect) = homeFeatureSlice.reducer(state, action)
        val (movieDetailsState, movieDetailsEffect) = movieDetailsFeatureSlice.reducer(
            state,
            action
        )

        val newGlobalState =
            state.copy(homeState = homeState, movieDetailsState = movieDetailsState)
        newGlobalState to Effects.merge(homeEffect, movieDetailsEffect)
    }
}
