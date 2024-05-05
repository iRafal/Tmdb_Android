package com.tmdb.feature.home.reducer

import com.tmdb.data.model.state.DataState
import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.feature.HomeFeature
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class HomeFeatureEffects(private val dispatcher: CoroutineDispatcher) {
    fun loadMovieSections() = mainEffect {
        withContext(dispatcher) {
            val nowPlayingMovies = async { env.network.movieSource.nowPlayingMovies() }.await()
            val nowPopularMovies = async { env.network.movieSource.nowPopularMovies() }.await()
            val topRatedMovies = async { env.network.movieSource.topRatedMovies() }.await()
            val upcomingMovies = async { env.network.movieSource.upcomingMovies() }.await()

            env.database.movieSource.insertByCategories(
                nowPlaying = nowPlayingMovies.getDataIfSuccessOrDefault(),
                nowPopular = nowPopularMovies.getDataIfSuccessOrDefault(),
                topRatedMovies = topRatedMovies.getDataIfSuccessOrDefault(),
                upcomingMovies = upcomingMovies.getDataIfSuccessOrDefault()
            )

            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = nowPlayingMovies,
                nowPopularMovies = nowPopularMovies,
                topRatedMovies = topRatedMovies,
                upcomingMovies = upcomingMovies
            )
        }
    }

    private fun <T> DataState<List<T>>.getDataIfSuccessOrDefault(): List<T> {
        return if (this.isSuccess) (this as DataState.Success).data else listOf()
    }

    private fun mainEffect(
        effectExecutorScope: suspend Effect.Scope<AppEnv>.() -> Action
    ): Effect<AppEnv> = Effects.effect(effectExecutorScope, HomeFeature)
}
