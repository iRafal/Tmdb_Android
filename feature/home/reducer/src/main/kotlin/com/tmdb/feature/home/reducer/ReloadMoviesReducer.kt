package com.tmdb.feature.home.reducer

import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.store.base.Effect
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceReloadMovies(
    homeFeatureEffects: HomeFeatureEffects
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return this.copyAsFullReload to homeFeatureEffects.loadMovieGroups()
}
