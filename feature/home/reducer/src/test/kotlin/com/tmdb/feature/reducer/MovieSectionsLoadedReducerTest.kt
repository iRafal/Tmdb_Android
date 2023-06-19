package com.tmdb.feature.reducer

import com.tmdb.api.model.util.ApiException
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.feature.reducer.util.ModelUtil
import com.tmdb.store.action.home.HomeAction
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.FeatureState
import com.tmdb.store.state.app.AppState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieSectionsLoadedReducerTest {

    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @Test
    fun `reduce movie sections loaded success`() = runTest {
        val dataMovies = listOf(ModelUtil.movieDataModel)

        val dataSuccessMovies = DataState.Success(dataMovies)

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading()
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataSuccessMovies,
            nowPopularMovies = dataSuccessMovies,
            topRatedMovies = dataSuccessMovies,
            upcomingMovies = dataSuccessMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataSuccessMovies },
            moviesDataToFeatureStateMapper = { FeatureState.Success(dataMovies) },
            homeFeatureEffects
        )

        val (homeFeatureState, effect) = homeFeatureSlice.reducer(appState, action)

        assertSame(effect, Effects.empty<AppEnv>())

        assertTrue(homeFeatureState.nowPlayingMoviesState.isSuccess)
        assertTrue(homeFeatureState.nowPopularMoviesState.isSuccess)
        assertTrue(homeFeatureState.topRatedMoviesState.isSuccess)
        assertTrue(homeFeatureState.upcomingMoviesState.isSuccess)

        assertEquals(
            dataMovies,
            (homeFeatureState.nowPlayingMoviesState as FeatureState.Success).data
        )
        assertEquals(
            dataMovies,
            (homeFeatureState.nowPopularMoviesState as FeatureState.Success).data
        )
        assertEquals(
            dataMovies,
            (homeFeatureState.topRatedMoviesState as FeatureState.Success).data
        )
        assertEquals(
            dataMovies,
            (homeFeatureState.upcomingMoviesState as FeatureState.Success).data
        )
    }

    @Test
    fun `reduce movie sections loaded network error`() = runTest {
        val dataNetworkErrorMovies = DataState.NetworkError<List<MovieDataModel>>()

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading()
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataNetworkErrorMovies,
            nowPopularMovies = dataNetworkErrorMovies,
            topRatedMovies = dataNetworkErrorMovies,
            upcomingMovies = dataNetworkErrorMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataNetworkErrorMovies },
            moviesDataToFeatureStateMapper = {
                FeatureState.NetworkError(ApiException.NetworkError())
            },
            homeFeatureEffects
        )

        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            action
        )

        assertSame(effect, Effects.empty<AppEnv>())

        assertTrue(homeFeatureState.nowPlayingMoviesState.isNetworkError)
        assertTrue(homeFeatureState.nowPopularMoviesState.isNetworkError)
        assertTrue(homeFeatureState.topRatedMoviesState.isNetworkError)
        assertTrue(homeFeatureState.upcomingMoviesState.isNetworkError)

        assertTrue(
            (homeFeatureState.nowPlayingMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
        assertTrue(
            (homeFeatureState.nowPopularMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
        assertTrue(
            (homeFeatureState.topRatedMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
        assertTrue(
            (homeFeatureState.upcomingMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
    }

    @Test
    fun `reduce movie sections loaded api error`() = runTest {
        val dataApiErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.BadRequest())

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading()
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataApiErrorMovies,
            nowPopularMovies = dataApiErrorMovies,
            topRatedMovies = dataApiErrorMovies,
            upcomingMovies = dataApiErrorMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataApiErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.Error(ApiException.BadRequest()) },
            homeFeatureEffects
        )

        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            action
        )

        assertSame(effect, Effects.empty<AppEnv>())

        assertTrue(homeFeatureState.nowPlayingMoviesState.isError)
        assertTrue(homeFeatureState.nowPopularMoviesState.isError)
        assertTrue(homeFeatureState.topRatedMoviesState.isError)
        assertTrue(homeFeatureState.upcomingMoviesState.isError)

        assertTrue((homeFeatureState.nowPlayingMoviesState as FeatureState.Error).cause is ApiException.BadRequest)
        assertTrue((homeFeatureState.nowPopularMoviesState as FeatureState.Error).cause is ApiException.BadRequest)
        assertTrue((homeFeatureState.topRatedMoviesState as FeatureState.Error).cause is ApiException.BadRequest)
        assertTrue((homeFeatureState.upcomingMoviesState as FeatureState.Error).cause is ApiException.BadRequest)
    }

    @Test
    fun `reduce movie sections loaded unknown error`() = runTest {
        val dataUnknownErrorMovies =
            DataState.Error<List<MovieDataModel>>(ApiException.UnknownError())

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading()
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataUnknownErrorMovies,
            nowPopularMovies = dataUnknownErrorMovies,
            topRatedMovies = dataUnknownErrorMovies,
            upcomingMovies = dataUnknownErrorMovies
        )

        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataUnknownErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.Error(ApiException.UnknownError()) },
            homeFeatureEffects
        )

        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            action
        )

        assertSame(effect, Effects.empty<AppEnv>())

        assertTrue(homeFeatureState.nowPlayingMoviesState.isError)
        assertTrue(homeFeatureState.nowPopularMoviesState.isError)
        assertTrue(homeFeatureState.topRatedMoviesState.isError)
        assertTrue(homeFeatureState.upcomingMoviesState.isError)

        assertTrue((homeFeatureState.nowPlayingMoviesState as FeatureState.Error).cause is ApiException.UnknownError)
        assertTrue((homeFeatureState.nowPopularMoviesState as FeatureState.Error).cause is ApiException.UnknownError)
        assertTrue((homeFeatureState.topRatedMoviesState as FeatureState.Error).cause is ApiException.UnknownError)
        assertTrue((homeFeatureState.upcomingMoviesState as FeatureState.Error).cause is ApiException.UnknownError)
    }
}
