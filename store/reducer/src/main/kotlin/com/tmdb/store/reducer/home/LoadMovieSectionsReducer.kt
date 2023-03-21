package com.tmdb.store.reducer.home

import com.tmdb.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb.store.action.home.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.FeatureState
import com.tmdb.store.state.home.HomeFeatureState


fun HomeFeatureState.reduceLoadMovieSections(
    action: HomeAction.LoadMovieSections,
    mapper: MoviesApiToDataStateMapper,
    homeFeatureEffects: HomeFeatureEffects
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        nowPlayingMoviesState = FeatureState.Loading(),
        nowPopularMoviesState = FeatureState.Loading(),
        topRatedMoviesState = FeatureState.Loading(),
        upcomingMoviesState = FeatureState.Loading(),
    )
    return newState to homeFeatureEffects.loadMovieSections(mapper)
}