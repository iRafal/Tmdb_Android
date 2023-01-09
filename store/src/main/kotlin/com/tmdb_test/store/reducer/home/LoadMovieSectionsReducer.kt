package com.tmdb_test.store.reducer.home

import com.tmdb_test.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.store.state.FeatureState
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.action.home.HomeAction
import com.tmdb_test.store.state.home.HomeFeatureState


fun HomeFeatureState.reduceLoadMovieSections(
    action: HomeAction.LoadMovieSections,
    mapper: MoviesApiToDataStateMapper
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        nowPlayingMoviesState = FeatureState.Loading(),
        nowPopularMoviesState = FeatureState.Loading(),
        topRatedMoviesState = FeatureState.Loading(),
        upcomingMoviesState = FeatureState.Loading(),
    )
    return newState to HomeFeatureEffects.loadMovieSections(mapper)
}