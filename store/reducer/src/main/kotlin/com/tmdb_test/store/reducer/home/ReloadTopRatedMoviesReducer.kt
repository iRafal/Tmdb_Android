package com.tmdb_test.store.reducer.home

import com.tmdb_test.store.action.home.HomeAction
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.env.contract.AppEnv
import com.tmdb_test.store.state.FeatureState
import com.tmdb_test.store.state.home.HomeFeatureState


fun HomeFeatureState.reduceReloadTopRatedMovies(
    action: HomeAction.ReloadTopRatedMovies
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return this.copy(topRatedMoviesState = FeatureState.Loading()) to Effects.empty() //TODO effect
}