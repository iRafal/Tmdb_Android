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
import org.mockito.kotlin.whenever

class LoadMovieSectionsReducerTest {
    private val mockExecutorHolder = MockExecutorHolder()
    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @Test
    fun `reduce load movie sections success`() = runTest {
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.reduce(appState, HomeAction.LoadMovieSections)

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.isLoading)
            assertTrue(popularMovies.isLoading)
            assertTrue(topRatedMovies.isLoading)
            assertTrue(upcomingMovies.isLoading)
        }

        val movies = listOf(ModelUtil.movieDataModel)
        val dataSuccessMovies = DataState.Success(movies)
        with(mockExecutorHolder.movieRemoteSource) {
            whenever(nowPlayingMovies()).thenReturn(dataSuccessMovies)
            whenever(nowPopularMovies()).thenReturn(dataSuccessMovies)
            whenever(topRatedMovies()).thenReturn(dataSuccessMovies)
            whenever(upcomingMovies()).thenReturn(dataSuccessMovies)
        }

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).nowPlayingMovies()
            verify(this, times(1)).nowPopularMovies()
            verify(this, times(1)).topRatedMovies()
            verify(this, times(1)).upcomingMovies()
        }

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataSuccessMovies,
                popularMovies = dataSuccessMovies,
                topRatedMovies = dataSuccessMovies,
                upcomingMovies = dataSuccessMovies
            )
        )
    }

    @Test
    fun `reduce load movie sections api error`() = runTest {
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.reduce(appState, HomeAction.LoadMovieSections)

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.isLoading)
            assertTrue(popularMovies.isLoading)
            assertTrue(topRatedMovies.isLoading)
            assertTrue(upcomingMovies.isLoading)
        }

        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.BadRequest())
        with(mockExecutorHolder.movieRemoteSource) {
            whenever(nowPlayingMovies()).thenReturn(dataErrorMovies)
            whenever(nowPopularMovies()).thenReturn(dataErrorMovies)
            whenever(topRatedMovies()).thenReturn(dataErrorMovies)
            whenever(upcomingMovies()).thenReturn(dataErrorMovies)
        }

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).nowPlayingMovies()
            verify(this, times(1)).nowPopularMovies()
            verify(this, times(1)).topRatedMovies()
            verify(this, times(1)).upcomingMovies()
        }

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                popularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies
            )
        )
    }

    @Test
    fun `reduce load movie sections network error`() = runTest {
        val dataErrorMovies = DataState.NetworkError<List<MovieDataModel>>(ApiException.NetworkError())
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.reduce(appState, HomeAction.LoadMovieSections)

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.isLoading)
            assertTrue(popularMovies.isLoading)
            assertTrue(topRatedMovies.isLoading)
            assertTrue(upcomingMovies.isLoading)
        }

        with(mockExecutorHolder.movieRemoteSource) {
            whenever(nowPlayingMovies()).thenReturn(dataErrorMovies)
            whenever(nowPopularMovies()).thenReturn(dataErrorMovies)
            whenever(topRatedMovies()).thenReturn(dataErrorMovies)
            whenever(upcomingMovies()).thenReturn(dataErrorMovies)
        }

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).nowPlayingMovies()
            verify(this, times(1)).nowPopularMovies()
            verify(this, times(1)).topRatedMovies()
            verify(this, times(1)).upcomingMovies()
        }

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                popularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies
            )
        )
    }

    @Test
    fun `reduce load movie sections unknown error`() = runTest {
        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.UnknownError())
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL
        val (homeFeatureState, effect) = homeFeatureReducer.reduce(appState, HomeAction.LoadMovieSections)

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.isLoading)
            assertTrue(popularMovies.isLoading)
            assertTrue(topRatedMovies.isLoading)
            assertTrue(upcomingMovies.isLoading)
        }

        with(mockExecutorHolder.movieRemoteSource) {
            whenever(nowPlayingMovies()).thenReturn(dataErrorMovies)
            whenever(nowPopularMovies()).thenReturn(dataErrorMovies)
            whenever(topRatedMovies()).thenReturn(dataErrorMovies)
            whenever(upcomingMovies()).thenReturn(dataErrorMovies)
        }

        val executor = createMockEffectExecutor(
            mockExecutorHolder.discoverRemoteSource,
            mockExecutorHolder.genreRemoteSource,
            mockExecutorHolder.movieRemoteSource,
            mockExecutorHolder.personRemoteSource,
            mockExecutorHolder.movieLocalSource
        )
        effect?.invoke(executor)

        with(mockExecutorHolder.movieRemoteSource) {
            verify(this, times(1)).nowPlayingMovies()
            verify(this, times(1)).nowPopularMovies()
            verify(this, times(1)).topRatedMovies()
            verify(this, times(1)).upcomingMovies()
        }

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                popularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies
            )
        )
    }
}
