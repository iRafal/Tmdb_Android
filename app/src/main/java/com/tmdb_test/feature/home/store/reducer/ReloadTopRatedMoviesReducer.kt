package com.tmdb_test.feature.home.store.reducer

import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureState
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.env.AppEnv


fun HomeFeatureState.reduceReloadTopRatedMovies(
    action: HomeAction.ReloadTopRatedMovies
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return this.copy(topRatedMoviesState = FeatureState.Loading()) to Effects.empty() //TODO effect
}