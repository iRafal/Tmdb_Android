package com.tmdb.store.reducer.home

import com.tmdb.store.action.home.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.home.HomeFeatureState
import com.tmdb.store.state.home.MoviesDataToFeatureStateMapper


fun HomeFeatureState.reduceMovieSectionsLoaded(
    action: HomeAction.MovieSectionsLoaded,
    mapper: MoviesDataToFeatureStateMapper
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        nowPlayingMoviesState = mapper(action.nowPlayingMovies),
        nowPopularMoviesState = mapper(action.nowPopularMovies),
        topRatedMoviesState = mapper(action.topRatedMovies),
        upcomingMoviesState = mapper(action.upcomingMovies),
    )
    return newState to Effects.empty()
}