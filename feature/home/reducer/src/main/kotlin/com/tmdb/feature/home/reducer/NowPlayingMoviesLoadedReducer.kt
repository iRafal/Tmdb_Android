package com.tmdb.feature.home.reducer

import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceNowPlayingMoviesLoaded(
    action: HomeAction.NowPlayingMoviesLoaded
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        isFullReload = false,
        nowPlayingMovies = this.nowPlayingMovies.copy(isLoading = false, movies = action.nowPlayingMovies),
    )
    return newState to Effects.empty()
}
