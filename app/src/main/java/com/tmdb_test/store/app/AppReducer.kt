package com.tmdb_test.store.app

import com.tmdb_test.di.ServiceLocator
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.Reducer
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.feature.movie.details.store.MovieDetailsFeatureSlice

typealias AppReducer = Reducer<AppState, AppEnv>

val appReducer: AppReducer = { state, action ->
    val (homeState, homeEffect) = ServiceLocator.homeFeatureSlice.reducer(state, action)
    val (movieDetailsState, movieDetailsEffect) = MovieDetailsFeatureSlice.reducer(state, action)

    val newGlobalState = state.copy(homeState = homeState, movieDetailsState = movieDetailsState)
    newGlobalState to Effects.merge(homeEffect, movieDetailsEffect)
}