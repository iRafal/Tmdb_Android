package com.tmdb.feature.home.reducer

import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reducePopularMoviesLoaded(
    action: HomeAction.PopularMoviesLoaded
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        isFullReload = false,
        popularMovies = this.popularMovies.copy(isLoading = false, movies = action.popularMovies),
    )
    return newState to Effects.empty()
}
