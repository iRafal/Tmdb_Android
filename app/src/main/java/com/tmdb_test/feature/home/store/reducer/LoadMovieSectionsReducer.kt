package com.tmdb_test.feature.home.store.reducer

import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureState
import com.tmdb_test.feature.home.store.effect.HomeFeatureEffects
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.util.MoviesApiResponseToDataStateMapper


fun HomeFeatureState.reduceLoadMovieSections(
    action: HomeAction.LoadMovieSections,
    mapper: MoviesApiResponseToDataStateMapper
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    val newState = this.copy(
        nowPlayingMoviesState = FeatureState.Loading(),
        nowPopularMoviesState = FeatureState.Loading(),
        topRatedMoviesState = FeatureState.Loading(),
        upcomingMoviesState = FeatureState.Loading(),
    )
    return newState to HomeFeatureEffects.loadMovieSections(mapper)
}