package com.tmdb_test.feature.home.store.reducer

import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureState
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.util.MoviesDataStateToFeatureStateMapper


fun HomeFeatureState.reduceMovieSectionsLoaded(
    action: HomeAction.MovieSectionsLoaded,
    mapper: MoviesDataStateToFeatureStateMapper
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        nowPlayingMoviesState = mapper(action.nowPlayingMovies),
        nowPopularMoviesState = mapper(action.nowPopularMovies),
        topRatedMoviesState = mapper(action.topRatedMovies),
        upcomingMoviesState = mapper(action.upcomingMovies),
    )
    return newState to Effects.empty()
}