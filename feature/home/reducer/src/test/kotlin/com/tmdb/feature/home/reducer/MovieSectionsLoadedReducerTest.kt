package com.tmdb.feature.home.reducer

import com.tmdb.api.model.util.ApiException
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.feature.home.reducer.util.ModelUtil
import com.tmdb.store.action.HomeAction
import com.tmdb.store.base.Effects
import com.tmdb.store.state.AppState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest

class MovieSectionsLoadedReducerTest {
    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @Test
    fun `reduce movie sections loaded success`() = runTest {
        val dataMovies = listOf(ModelUtil.movieDataModel)

        val dataSuccessMovies = DataState.Success(dataMovies)

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataSuccessMovies,
            popularMovies = dataSuccessMovies,
            topRatedMovies = dataSuccessMovies,
            upcomingMovies = dataSuccessMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copyAsAllLoading
        )
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, action)

        assertSame(expected = effect, actual = Effects.empty())

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.movies?.isSuccess == true)
            assertTrue(popularMovies.movies?.isSuccess == true)
            assertTrue(topRatedMovies.movies?.isSuccess == true)
            assertTrue(upcomingMovies.movies?.isSuccess == true)

            assertEquals(expected = dataMovies, actual = (nowPlayingMovies.movies as DataState.Success).data)
            assertEquals(expected = dataMovies, actual = (popularMovies.movies as DataState.Success).data)
            assertEquals(expected = dataMovies, actual = (topRatedMovies.movies as DataState.Success).data)
            assertEquals(expected = dataMovies, actual = (upcomingMovies.movies as DataState.Success).data)
        }
    }

    @Test
    fun `reduce movie sections loaded network error`() = runTest {
        val expectedException = ApiException.NetworkError()
        val dataNetworkErrorMovies = DataState.NetworkError<List<MovieDataModel>>(expectedException)

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataNetworkErrorMovies,
            popularMovies = dataNetworkErrorMovies,
            topRatedMovies = dataNetworkErrorMovies,
            upcomingMovies = dataNetworkErrorMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val appState = AppState.INITIAL.copy(homeState = AppState.INITIAL.homeState.copyAsAllLoading)
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, action)

        assertSame(expected = effect, actual = Effects.empty())

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.movies?.isNetworkError == true)
            assertTrue(popularMovies.movies?.isNetworkError == true)
            assertTrue(topRatedMovies.movies?.isNetworkError == true)
            assertTrue(upcomingMovies.movies?.isNetworkError == true)

            assertTrue((nowPlayingMovies.movies as DataState.NetworkError).cause is ApiException.NetworkError)
            assertTrue((popularMovies.movies as DataState.NetworkError).cause is ApiException.NetworkError)
            assertTrue((topRatedMovies.movies as DataState.NetworkError).cause is ApiException.NetworkError)
            assertTrue((upcomingMovies.movies as DataState.NetworkError).cause is ApiException.NetworkError)
        }
    }

    @Test
    fun `reduce movie sections loaded api error`() = runTest {
        val dataApiErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.BadRequest())

        val appState = AppState.INITIAL.copy(homeState = AppState.INITIAL.homeState.copyAsAllLoading)

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataApiErrorMovies,
            popularMovies = dataApiErrorMovies,
            topRatedMovies = dataApiErrorMovies,
            upcomingMovies = dataApiErrorMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, action)

        assertSame(expected = effect, actual = Effects.empty())

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.movies?.isError == true)
            assertTrue(popularMovies.movies?.isError == true)
            assertTrue(topRatedMovies.movies?.isError == true)
            assertTrue(upcomingMovies.movies?.isError == true)

            assertTrue((nowPlayingMovies.movies as DataState.Error).cause is ApiException.BadRequest)
            assertTrue((popularMovies.movies as DataState.Error).cause is ApiException.BadRequest)
            assertTrue((topRatedMovies.movies as DataState.Error).cause is ApiException.BadRequest)
            assertTrue((upcomingMovies.movies as DataState.Error).cause is ApiException.BadRequest)
        }
    }

    @Test
    fun `reduce movie sections loaded unknown error`() = runTest {
        val dataUnknownErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.UnknownError())

        val appState = AppState.INITIAL.copy(homeState = AppState.INITIAL.homeState.copyAsAllLoading)

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataUnknownErrorMovies,
            popularMovies = dataUnknownErrorMovies,
            topRatedMovies = dataUnknownErrorMovies,
            upcomingMovies = dataUnknownErrorMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureReducer = HomeFeatureReducer(homeFeatureEffects)
        val (homeFeatureState, effect) = homeFeatureReducer.map(appState, action)

        assertSame(expected = effect, actual = Effects.empty())

        with(homeFeatureState) {
            assertTrue(nowPlayingMovies.movies?.isError == true)
            assertTrue(popularMovies.movies?.isError == true)
            assertTrue(topRatedMovies.movies?.isError == true)
            assertTrue(upcomingMovies.movies?.isError == true)

            assertTrue((nowPlayingMovies.movies as DataState.Error).cause is ApiException.UnknownError)
            assertTrue((popularMovies.movies as DataState.Error).cause is ApiException.UnknownError)
            assertTrue((topRatedMovies.movies as DataState.Error).cause is ApiException.UnknownError)
            assertTrue((upcomingMovies.movies as DataState.Error).cause is ApiException.UnknownError)
        }
    }
}
