package com.tmdb_test.store.reducer.home

import com.tmdb_test.store.action.home.HomeAction
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.state.home.HomeFeatureState
import com.tmdb_test.store.state.home.MoviesDataToFeatureStateMapper


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