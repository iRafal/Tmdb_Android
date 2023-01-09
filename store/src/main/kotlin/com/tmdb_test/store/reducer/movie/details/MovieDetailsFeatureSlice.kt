package com.tmdb_test.store.reducer.movie.details

import com.tmdb_test.store.action.movie.details.MovieDetailsAction
import com.tmdb_test.store.base.Action
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.feature.FeatureReducer
import com.tmdb_test.store.base.feature.FeatureSlice
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.state.AppState
import com.tmdb_test.store.state.movie.details.MovieDetailsFeatureState

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