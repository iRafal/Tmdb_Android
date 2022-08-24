package com.tmdb_test.feature.home.store.reducer

import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiException
import com.tmdb_test.data.api.util.ApiException.BadRequest
import com.tmdb_test.data.api.util.ApiException.UnknownError
import com.tmdb_test.data.util.DataState
import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.FeatureState.Error
import com.tmdb_test.store.FeatureState.NetworkError
import com.tmdb_test.store.FeatureState.Success
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.util.model.ModelUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieSectionsLoadedReducerTest {

    @Test
    fun `reduce movie sections loaded success`() = runTest {
        val movies = listOf(ModelUtil.movieModel)
        val dataSuccessMovies = DataState.Success(movies)

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading(),
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataSuccessMovies,
            nowPopularMovies = dataSuccessMovies,
            topRatedMovies = dataSuccessMovies,
            upcomingMovies = dataSuccessMovies,
        )

        val homeFeatureSlice = HomeFeatureSlice(
            moviesApiResponseToDataStateMapper = {
                dataSuccessMovies
            },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.Success(movies)
            },
        )

        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            action
        )

        assertSame(effect, Effects.empty<AppEnv>())

        assertTrue(homeFeatureState.nowPlayingMoviesState.isSuccess)
        assertTrue(homeFeatureState.nowPopularMoviesState.isSuccess)
        assertTrue(homeFeatureState.topRatedMoviesState.isSuccess)
        assertTrue(homeFeatureState.upcomingMoviesState.isSuccess)

        assertEquals(
            movies,
            (homeFeatureState.nowPlayingMoviesState as Success).data
        )
        assertEquals(
            movies,
            (homeFeatureState.nowPopularMoviesState as Success).data
        )
        assertEquals(
            movies,
            (homeFeatureState.topRatedMoviesState as Success).data
        )
        assertEquals(
            movies,
            (homeFeatureState.upcomingMoviesState as Success).data
        )
    }

    @Test
    fun `reduce movie sections loaded network error`() = runTest {
        val dataNetworkErrorMovies = DataState.NetworkError<List<Movie>>()

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading(),
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataNetworkErrorMovies,
            nowPopularMovies = dataNetworkErrorMovies,
            topRatedMovies = dataNetworkErrorMovies,
            upcomingMovies = dataNetworkErrorMovies,
        )

        val homeFeatureSlice = HomeFeatureSlice(
            moviesApiResponseToDataStateMapper = { dataNetworkErrorMovies },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.NetworkError(ApiException.NetworkError())
            },
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
            (homeFeatureState.nowPlayingMoviesState as NetworkError).cause is ApiException.NetworkError
        )
        assertTrue(
            (homeFeatureState.nowPopularMoviesState as NetworkError).cause is ApiException.NetworkError
        )
        assertTrue(
            (homeFeatureState.topRatedMoviesState as NetworkError).cause is ApiException.NetworkError
        )
        assertTrue(
            (homeFeatureState.upcomingMoviesState as NetworkError).cause is ApiException.NetworkError
        )
    }

    @Test
    fun `reduce movie sections loaded api error`() = runTest {
        val dataApiErrorMovies = DataState.Error<List<Movie>>(ApiException.BadRequest())

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading(),
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataApiErrorMovies,
            nowPopularMovies = dataApiErrorMovies,
            topRatedMovies = dataApiErrorMovies,
            upcomingMovies = dataApiErrorMovies,
        )

        val homeFeatureSlice = HomeFeatureSlice(
            moviesApiResponseToDataStateMapper = {
                dataApiErrorMovies
            },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.Error(ApiException.BadRequest())
            },
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

        assertTrue((homeFeatureState.nowPlayingMoviesState as Error).cause is BadRequest)
        assertTrue((homeFeatureState.nowPopularMoviesState as Error).cause is BadRequest)
        assertTrue((homeFeatureState.topRatedMoviesState as Error).cause is BadRequest)
        assertTrue((homeFeatureState.upcomingMoviesState as Error).cause is BadRequest)
    }

    @Test
    fun `reduce movie sections loaded unknown error`() = runTest {
        val dataUnknownErrorMovies = DataState.Error<List<Movie>>(ApiException.UnknownError())

        val appState = AppState.INITIAL.copy(
            homeState = AppState.INITIAL.homeState.copy(
                nowPlayingMoviesState = FeatureState.Loading(),
                nowPopularMoviesState = FeatureState.Loading(),
                topRatedMoviesState = FeatureState.Loading(),
                upcomingMoviesState = FeatureState.Loading(),
            )
        )

        val action = HomeAction.MovieSectionsLoaded(
            nowPlayingMovies = dataUnknownErrorMovies,
            nowPopularMovies = dataUnknownErrorMovies,
            topRatedMovies = dataUnknownErrorMovies,
            upcomingMovies = dataUnknownErrorMovies,
        )

        val homeFeatureSlice = HomeFeatureSlice(
            moviesApiResponseToDataStateMapper = {
                dataUnknownErrorMovies
            },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.Error(ApiException.UnknownError())
            },
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

        assertTrue((homeFeatureState.nowPlayingMoviesState as Error).cause is UnknownError)
        assertTrue((homeFeatureState.nowPopularMoviesState as Error).cause is UnknownError)
        assertTrue((homeFeatureState.topRatedMoviesState as Error).cause is UnknownError)
        assertTrue((homeFeatureState.upcomingMoviesState as Error).cause is UnknownError)
    }
}