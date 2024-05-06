package com.tmdb.feature.home.reducer

import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.base.feature.FeatureReducer
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.AppState
import com.tmdb.store.state.HomeFeatureState

class HomeFeatureReducer(
    private val homeFeatureEffects: HomeFeatureEffects
) : FeatureReducer<AppState, AppEnv, HomeFeatureState> {
    override fun reduce(globalState: AppState, action: Action): Pair<HomeFeatureState, Effect<AppEnv>?> {
        return when (action) {
            is HomeAction -> globalState.reduce(action, homeFeatureEffects)
            else -> globalState.homeState to Effects.empty()
        }
    }

    private fun AppState.reduce(
        action: HomeAction,
        homeFeatureEffects: HomeFeatureEffects
    ): Pair<HomeFeatureState, Effect<AppEnv>?> {
        return when (action) {
            is HomeAction.ReloadNowPlayingMovies -> homeState.reduceReloadNowPlayingMovies(homeFeatureEffects)
            is HomeAction.NowPlayingMoviesLoaded -> homeState.reduceNowPlayingMoviesLoaded(action)

            is HomeAction.ReloadPopularMovies -> homeState.reduceReloadPopularMovies(homeFeatureEffects)
            is HomeAction.PopularMoviesLoaded -> homeState.reducePopularMoviesLoaded(action)

            is HomeAction.ReloadTopRatedMovies -> homeState.reduceReloadTopRatedMovies(homeFeatureEffects)
            is HomeAction.TopRatedMoviesLoaded -> homeState.reduceTopRatedMoviesLoaded(action)

            is HomeAction.ReloadUpcomingMovies -> homeState.reduceReloadUpcomingMovies(homeFeatureEffects)
            is HomeAction.UpcomingMoviesLoaded -> homeState.reduceUpcomingMoviesLoaded(action)

            is HomeAction.LoadMovieSections -> homeState.reduceLoadMovieSections(homeFeatureEffects)
            is HomeAction.MovieSectionsLoaded -> homeState.reduceMovieSectionsLoaded(action)

            is HomeAction.ReloadAllMovies -> homeState.reduceReloadMovies(homeFeatureEffects)

            is HomeAction.ResetState -> homeState.reduceHomeResetState()
        }
    }
}
