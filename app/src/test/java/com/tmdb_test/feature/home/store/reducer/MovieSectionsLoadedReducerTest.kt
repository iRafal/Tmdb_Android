package com.tmdb_test.feature.home.store.reducer

import com.tmdb_test.data.api.ModelUtil
import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiException
import com.tmdb_test.data.util.DataState
import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.env.AppEnv
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MovieSectionsLoadedReducerTest {

    @Test
    fun `reduce movie sections loaded success`() = runBlocking {
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

        Assert.assertSame(effect, Effects.empty<AppEnv>())

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isSuccess)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isSuccess)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isSuccess)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isSuccess)

        Assert.assertEquals(
            movies,
            (homeFeatureState.nowPlayingMoviesState as FeatureState.Success).data
        )
        Assert.assertEquals(
            movies,
            (homeFeatureState.nowPopularMoviesState as FeatureState.Success).data
        )
        Assert.assertEquals(
            movies,
            (homeFeatureState.topRatedMoviesState as FeatureState.Success).data
        )
        Assert.assertEquals(
            movies,
            (homeFeatureState.upcomingMoviesState as FeatureState.Success).data
        )
    }

    @Test
    fun `reduce movie sections loaded network error`() = runBlocking {
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
            moviesApiResponseToDataStateMapper = {
                dataNetworkErrorMovies
            },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.NetworkError(ApiException.NetworkError())
            },
        )

        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            action
        )

        Assert.assertSame(effect, Effects.empty<AppEnv>())

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isNetworkError)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isNetworkError)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isNetworkError)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isNetworkError)

        Assert.assertTrue(
            (homeFeatureState.nowPlayingMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
        Assert.assertTrue(
            (homeFeatureState.nowPopularMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
        Assert.assertTrue(
            (homeFeatureState.topRatedMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
        Assert.assertTrue(
            (homeFeatureState.upcomingMoviesState as FeatureState.NetworkError).cause is ApiException.NetworkError
        )
    }

    @Test
    fun `reduce movie sections loaded api error`() = runBlocking {
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

        Assert.assertSame(effect, Effects.empty<AppEnv>())

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isError)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isError)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isError)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isError)

        Assert.assertTrue(
            (homeFeatureState.nowPlayingMoviesState as FeatureState.Error).cause is ApiException.BadRequest
        )
        Assert.assertTrue(
            (homeFeatureState.nowPopularMoviesState as FeatureState.Error).cause is ApiException.BadRequest
        )
        Assert.assertTrue(
            (homeFeatureState.topRatedMoviesState as FeatureState.Error).cause is ApiException.BadRequest
        )
        Assert.assertTrue(
            (homeFeatureState.upcomingMoviesState as FeatureState.Error).cause is ApiException.BadRequest
        )
    }

    @Test
    fun `reduce movie sections loaded unknown error`() = runBlocking {
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

        Assert.assertSame(effect, Effects.empty<AppEnv>())

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isError)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isError)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isError)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isError)

        Assert.assertTrue(
            (homeFeatureState.nowPlayingMoviesState as FeatureState.Error).cause is ApiException.UnknownError
        )
        Assert.assertTrue(
            (homeFeatureState.nowPopularMoviesState as FeatureState.Error).cause is ApiException.UnknownError
        )
        Assert.assertTrue(
            (homeFeatureState.topRatedMoviesState as FeatureState.Error).cause is ApiException.UnknownError
        )
        Assert.assertTrue(
            (homeFeatureState.upcomingMoviesState as FeatureState.Error).cause is ApiException.UnknownError
        )
    }
}