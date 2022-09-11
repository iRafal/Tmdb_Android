package com.tmdb_test.feature.home.store

import com.tmdb_test.data.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.feature.home.store.HomeAction.LoadMovieSections
import com.tmdb_test.feature.home.store.HomeAction.MovieSectionsLoaded
import com.tmdb_test.feature.home.store.HomeAction.ReloadNowPlayingMovies
import com.tmdb_test.feature.home.store.HomeAction.ReloadNowPopularMovies
import com.tmdb_test.feature.home.store.HomeAction.ReloadTopRatedMovies
import com.tmdb_test.feature.home.store.HomeAction.ReloadUpcomingMovies
import com.tmdb_test.feature.home.store.reducer.reduceLoadMovieSections
import com.tmdb_test.feature.home.store.reducer.reduceMovieSectionsLoaded
import com.tmdb_test.feature.home.store.reducer.reduceReloadNowPlayingMovies
import com.tmdb_test.feature.home.store.reducer.reduceReloadNowPopularMovies
import com.tmdb_test.feature.home.store.reducer.reduceReloadTopRatedMovies
import com.tmdb_test.feature.home.store.reducer.reduceReloadUpcomingMovies
import com.tmdb_test.feature.mapping.MoviesDataToFeatureStateMapper
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.base.Action
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.feature.FeatureReducer
import com.tmdb_test.store.base.feature.FeatureSlice
import com.tmdb_test.store.env.AppEnv

interface HomeFeatureSlice : FeatureSlice<AppState, AppEnv, HomeFeatureState>

class HomeFeatureSliceImpl(
    moviesApiToDataStateMapper: MoviesApiToDataStateMapper,
    moviesDataToFeatureStateMapper: MoviesDataToFeatureStateMapper,
) : HomeFeatureSlice {

    override val reducer: FeatureReducer<AppState, AppEnv, HomeFeatureState> =
        { globalState: AppState,
          action: Action ->
            when (action) {
                is HomeAction -> {
                    globalState.reduce(
                        action,
                        moviesApiToDataStateMapper,
                        moviesDataToFeatureStateMapper
                    )
                }
                else -> globalState.homeState to Effects.empty()
            }
        }
}

private fun AppState.reduce(
    action: HomeAction,
    moviesApiToDataStateMapper: MoviesApiToDataStateMapper,
    moviesDataToFeatureStateMapper: MoviesDataToFeatureStateMapper
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return when (action) {
        is ReloadNowPlayingMovies -> this.homeState.reduceReloadNowPlayingMovies(action)
        is ReloadNowPopularMovies -> this.homeState.reduceReloadNowPopularMovies(action)
        is ReloadTopRatedMovies -> this.homeState.reduceReloadTopRatedMovies(action)
        is ReloadUpcomingMovies -> this.homeState.reduceReloadUpcomingMovies(action)
        is LoadMovieSections -> {
            this.homeState.reduceLoadMovieSections(action, moviesApiToDataStateMapper)
        }
        is MovieSectionsLoaded -> {
            this.homeState.reduceMovieSectionsLoaded(action, moviesDataToFeatureStateMapper)
        }
    }
}
