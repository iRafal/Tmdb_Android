package com.tmdb.feature.home.reducer

import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

fun HomeFeatureState.reduceUpcomingMoviesLoaded(
    action: HomeAction.UpcomingMoviesLoaded
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        isFullReload = false,
        popularMovies = this.upcomingMovies.copy(isLoading = false, movies = action.upcomingMovies),
    )
    return newState to Effects.empty()
}
