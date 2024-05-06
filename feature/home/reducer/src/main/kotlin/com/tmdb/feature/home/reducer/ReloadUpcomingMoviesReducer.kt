package com.tmdb.feature.home.reducer

import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.store.base.Effect
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceReloadUpcomingMovies(
    homeFeatureEffects: HomeFeatureEffects
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(upcomingMovies = upcomingMovies.copyAsLoading)
    return newState to homeFeatureEffects.loadUpcomingMovies()
}
