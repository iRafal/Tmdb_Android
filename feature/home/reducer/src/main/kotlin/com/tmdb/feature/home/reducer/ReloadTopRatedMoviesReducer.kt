package com.tmdb.feature.home.reducer

import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.store.base.Effect
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceReloadTopRatedMovies(
    homeFeatureEffects: HomeFeatureEffects
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(topRatedMovies = topRatedMovies.copyAsLoading)
    return newState to homeFeatureEffects.loadTopRatedMovies()
}
