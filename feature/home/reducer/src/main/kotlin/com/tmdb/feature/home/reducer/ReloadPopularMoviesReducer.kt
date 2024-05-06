package com.tmdb.feature.home.reducer

import com.tmdb.store.base.Effect
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceReloadPopularMovies(
    homeFeatureEffects: HomeFeatureEffects
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(popularMovies = popularMovies.copyAsLoading)
    return newState to homeFeatureEffects.loadPopularMovies()
}
