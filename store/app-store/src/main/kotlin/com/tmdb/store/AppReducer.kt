package com.tmdb.store

import com.tmdb.feature.home.reducer.HomeFeatureReducer
import com.tmdb.feature.movie.details.reducer.MovieDetailsFeatureSlice
import com.tmdb.store.base.Action
import com.tmdb.store.base.Effects
import com.tmdb.store.base.ReducedResult
import com.tmdb.store.base.Reducer
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.AppState
import javax.inject.Inject

fun interface AppReducer: Reducer<AppState, AppEnv>

class AppReducerImpl @Inject constructor(
    private val homeFeatureReducer: HomeFeatureReducer,
    private val movieDetailsFeatureSlice: MovieDetailsFeatureSlice
): AppReducer {
    override fun reduce(state: AppState, action: Action): ReducedResult<AppState, AppEnv> {
        val (homeState, homeEffect) = homeFeatureReducer.map(state, action)
        val (movieDetailsState, movieDetailsEffect) = movieDetailsFeatureSlice.reducer.map(state, action)

        val newGlobalState =
            state.copy(homeState = homeState, movieDetailsState = movieDetailsState)
        return newGlobalState to Effects.merge(homeEffect, movieDetailsEffect)
    }
}
