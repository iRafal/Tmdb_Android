package com.tmdb.feature.home.reducer

import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.store.base.Effect
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceLoadMovieSections(
    homeFeatureEffects: HomeFeatureEffects
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copyAsAllLoading
    return newState to homeFeatureEffects.loadMovieGroups()
}
