package com.tmdb_test.feature.home.store.reducer

import com.tmdb_test.data.api.ModelUtil
import com.tmdb_test.data.api.util.NetworkErrorModel
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiException
import com.tmdb_test.data.api.util.ApiResponse
import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSource
import com.tmdb_test.data.util.DataState
import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.feature.home.store.effect.createMockEffectExecutor
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.app.AppState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoadMovieSectionsReducerTest {

    private val discoverSource = mock<DiscoverRemoteDataSource>()
    private val genreSource = mock<GenreRemoteDataSource>()
    private val movieSource = mock<MovieRemoteDataSource>()
    private val personSource = mock<PersonRemoteDataSource>()

    @Test
    fun `reduce load movie sections success`() = runBlocking {
        val movies = listOf(ModelUtil.movieModel)
        val successResult = ApiResponse.Success(
            DataPage(
                page = 1,
                results = movies,
                totalPages = 1,
                totalResults = 1,
            )
        )

        val appState = AppState.INITIAL
        val dataSuccessMovies = DataState.Success(movies)
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
            HomeAction.LoadMovieSections
        )

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieSource.nowPlayingMovies()).thenReturn(successResult)
        whenever(movieSource.nowPopularMovies()).thenReturn(successResult)
        whenever(movieSource.topRatedMovies()).thenReturn(successResult)
        whenever(movieSource.upcomingMovies()).thenReturn(successResult)

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource
        )
        effect?.invoke(executor)

        verify(movieSource, times(1)).nowPlayingMovies()
        verify(movieSource, times(1)).nowPopularMovies()
        verify(movieSource, times(1)).topRatedMovies()
        verify(movieSource, times(1)).upcomingMovies()
        
        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataSuccessMovies,
                nowPopularMovies = dataSuccessMovies,
                topRatedMovies = dataSuccessMovies,
                upcomingMovies = dataSuccessMovies,
            )
        )
    }

    @Test
    fun `reduce load movie sections api error`() = runBlocking {
        val apiErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.ApiError()

        val appState = AppState.INITIAL
        val dataErrorMovies = DataState.Error<List<Movie>>(ApiException.BadRequest())
        val homeFeatureSlice = HomeFeatureSlice(
            moviesApiResponseToDataStateMapper = {
                dataErrorMovies
            },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.Error()
            },
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieSource.nowPlayingMovies()).thenReturn(apiErrorResult)
        whenever(movieSource.nowPopularMovies()).thenReturn(apiErrorResult)
        whenever(movieSource.topRatedMovies()).thenReturn(apiErrorResult)
        whenever(movieSource.upcomingMovies()).thenReturn(apiErrorResult)

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource
        )
        effect?.invoke(executor)

        verify(movieSource, times(1)).nowPlayingMovies()
        verify(movieSource, times(1)).nowPopularMovies()
        verify(movieSource, times(1)).topRatedMovies()
        verify(movieSource, times(1)).upcomingMovies()

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                nowPopularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies,
            )
        )
    }

    @Test
    fun `reduce load movie sections network error`() = runBlocking {
        val networkErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.NetworkError()

        val appState = AppState.INITIAL
        val dataErrorMovies = DataState.NetworkError<List<Movie>>(ApiException.NetworkError())
        val homeFeatureSlice = HomeFeatureSlice(
            moviesApiResponseToDataStateMapper = {
                dataErrorMovies
            },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.NetworkError()
            },
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieSource.nowPlayingMovies()).thenReturn(networkErrorResult)
        whenever(movieSource.nowPopularMovies()).thenReturn(networkErrorResult)
        whenever(movieSource.topRatedMovies()).thenReturn(networkErrorResult)
        whenever(movieSource.upcomingMovies()).thenReturn(networkErrorResult)

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource
        )
        effect?.invoke(executor)

        verify(movieSource, times(1)).nowPlayingMovies()
        verify(movieSource, times(1)).nowPopularMovies()
        verify(movieSource, times(1)).topRatedMovies()
        verify(movieSource, times(1)).upcomingMovies()

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                nowPopularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies,
            )
        )
    }

    @Test
    fun `reduce load movie sections unknown error`() = runBlocking {
        val unknownErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.UnknownError()

        val appState = AppState.INITIAL
        val dataErrorMovies = DataState.Error<List<Movie>>(ApiException.UnknownError())
        val homeFeatureSlice = HomeFeatureSlice(
            moviesApiResponseToDataStateMapper = {
                dataErrorMovies
            },
            moviesDataStateToFeatureStateMapper = {
                FeatureState.NetworkError()
            },
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        Assert.assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        Assert.assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieSource.nowPlayingMovies()).thenReturn(unknownErrorResult)
        whenever(movieSource.nowPopularMovies()).thenReturn(unknownErrorResult)
        whenever(movieSource.topRatedMovies()).thenReturn(unknownErrorResult)
        whenever(movieSource.upcomingMovies()).thenReturn(unknownErrorResult)

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource
        )
        effect?.invoke(executor)

        verify(movieSource, times(1)).nowPlayingMovies()
        verify(movieSource, times(1)).nowPopularMovies()
        verify(movieSource, times(1)).topRatedMovies()
        verify(movieSource, times(1)).upcomingMovies()

        verify(executor.effectExecutorScope).dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                nowPopularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies,
            )
        )
    }
}