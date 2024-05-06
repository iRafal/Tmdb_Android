package com.tmdb.feature.home.reducer

import com.tmdb.api.model.util.ApiException
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.feature.home.reducer.util.ModelUtil
import com.tmdb.store.action.HomeAction
import com.tmdb.store.common.test.MockExecutorHolder
import com.tmdb.store.common.test.createMockEffectExecutor
import com.tmdb.store.state.AppState
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class ReloadTopRatedMoviesReducerTest {
    private val mockExecutorHolder = MockExecutorHolder()
    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @Test
    fun `reduce reload top rated movies success`() = runTest {
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, HomeAction.ReloadTopRatedMovies)

        assertTrue(homeFeatureState.topRatedMovies.isLoading)

        val movies = listOf(ModelUtil.movieDataModel)
        val dataSuccessMovies = DataState.Success(movies)
        whenever(mockExecutorHolder.movieRemoteSource.topRatedMovies()).thenReturn(dataSuccessMovies)

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).topRatedMovies()
            verifyNoMoreInteractions(this)
        }

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.TopRatedMoviesLoaded(
                topRatedMovies = dataSuccessMovies
            )
        )
    }

    @Test
    fun `reduce reload top rated movies api error`() = runTest {
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, HomeAction.ReloadTopRatedMovies)

        assertTrue(homeFeatureState.topRatedMovies.isLoading)

        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.BadRequest())
        whenever(mockExecutorHolder.movieRemoteSource.topRatedMovies()).thenReturn(dataErrorMovies)

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).topRatedMovies()
            verifyNoMoreInteractions(this)
        }

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.TopRatedMoviesLoaded(
                topRatedMovies = dataErrorMovies
            )
        )
    }

    @Test
    fun `reduce reload top rated movies network error`() = runTest {
        val dataErrorMovies = DataState.NetworkError<List<MovieDataModel>>(ApiException.NetworkError())
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, HomeAction.ReloadTopRatedMovies)

        assertTrue(homeFeatureState.topRatedMovies.isLoading)

        whenever(mockExecutorHolder.movieRemoteSource.topRatedMovies()).thenReturn(dataErrorMovies)

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).topRatedMovies()
            verifyNoMoreInteractions(this)
        }
        verify(executor.effectExecutorScope).dispatch(
            HomeAction.TopRatedMoviesLoaded(
                topRatedMovies = dataErrorMovies
            )
        )
    }

    @Test
    fun `reduce reload top rated movies unknown error`() = runTest {
        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.UnknownError())
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, HomeAction.ReloadTopRatedMovies)

        assertTrue(homeFeatureState.topRatedMovies.isLoading)

        whenever(mockExecutorHolder.movieRemoteSource.topRatedMovies()).thenReturn(dataErrorMovies)

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).topRatedMovies()
            verifyNoMoreInteractions(this)
        }
        verify(executor.effectExecutorScope).dispatch(
            HomeAction.TopRatedMoviesLoaded(
                topRatedMovies = dataErrorMovies
            )
        )
    }
}
