package com.tmdb.feature.movie.details.reducer

import com.tmdb.store.action.MovieDetailsAction
import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.base.feature.FeatureReducer
import com.tmdb.store.base.feature.FeatureSlice
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.AppState
import com.tmdb.store.state.MovieDetailsFeatureState

class MovieDetailsFeatureSlice : FeatureSlice<AppState, AppEnv, MovieDetailsFeatureState> {
    override val reducer: FeatureReducer<AppState, AppEnv, MovieDetailsFeatureState> =
        FeatureReducer { globalState: AppState, action: Action ->
            when (action) {
                is MovieDetailsAction -> reduce(action)
                else -> globalState.movieDetailsState to Effects.empty()
            }
        }
}

private fun reduce(action: MovieDetailsAction): Pair<MovieDetailsFeatureState, Effect<AppEnv>?> {
    return when (action) {
        else -> MovieDetailsFeatureState.INITIAL to Effects.empty() // TODO
    }
}
