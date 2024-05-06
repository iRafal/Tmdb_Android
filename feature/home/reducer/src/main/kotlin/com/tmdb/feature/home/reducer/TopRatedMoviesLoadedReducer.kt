package com.tmdb.feature.home.reducer

import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceTopRatedMoviesLoaded(
    action: HomeAction.TopRatedMoviesLoaded
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        isFullReload = false,
        topRatedMovies = this.topRatedMovies.copy(isLoading = false, movies = action.topRatedMovies),
    )
    return newState to Effects.empty()
}
