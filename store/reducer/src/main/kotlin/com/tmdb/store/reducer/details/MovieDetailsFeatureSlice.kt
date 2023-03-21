package com.tmdb.store.reducer.details

import com.tmdb.store.action.details.MovieDetailsAction
import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.base.feature.FeatureReducer
import com.tmdb.store.base.feature.FeatureSlice
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.app.AppState
import com.tmdb.store.state.details.MovieDetailsFeatureState

object MovieDetailsFeatureSlice : FeatureSlice<AppState, AppEnv, MovieDetailsFeatureState> {
    override val reducer: FeatureReducer<AppState, AppEnv, MovieDetailsFeatureState> =
        { globalState: AppState,
          action: Action ->
            when (action) {
                is MovieDetailsAction -> {
                    MovieDetailsFeatureState.INITIAL to Effects.empty() //TODO
                }
                else -> globalState.movieDetailsState to Effects.empty()
            }
        }
}

private fun AppState.reduce(action: MovieDetailsAction): Pair<MovieDetailsFeatureState, Effect<AppEnv>?> {
    return when(action) {
        else -> movieDetailsState to Effects.empty()
    }
}