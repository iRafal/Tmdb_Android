package com.tmdb_test.feature.home.store.reducer

import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiException
import com.tmdb_test.data.api.util.ApiResponse
import com.tmdb_test.data.api.util.NetworkErrorModel
import com.tmdb_test.data.model.DataState
import com.tmdb_test.data.model.MovieDataModel
import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSource
import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.feature.home.store.HomeFeatureSliceImpl
import com.tmdb_test.feature.home.store.effect.createMockEffectExecutor
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.FeatureState.Success
import com.tmdb_test.store.app.AppState
import com.tmdb_test.util.model.ModelUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class LoadMovieSectionsReducerTest {

    private val discoverSource = mock<DiscoverRemoteDataSource>()
    private val genreSource = mock<GenreRemoteDataSource>()
    private val movieSource = mock<MovieRemoteDataSource>()
    private val personSource = mock<PersonRemoteDataSource>()

    @Test
    fun `reduce load movie sections success`() = runTest {
        val movies = listOf(ModelUtil.movieDataModel)
        val successResult = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            )
        )

        val appState = AppState.INITIAL
        val dataSuccessMovies = DataState.Success(movies)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper =  { dataSuccessMovies },
            moviesDataToFeatureStateMapper = { Success(movies) }
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

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
    fun `reduce load movie sections api error`() = runTest {
        val apiErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.ApiError()

        val appState = AppState.INITIAL
        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.BadRequest())
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.Error() },
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

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
    fun `reduce load movie sections network error`() = runTest {
        val networkErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> =
            ApiResponse.NetworkError()

        val appState = AppState.INITIAL
        val dataErrorMovies =
            DataState.NetworkError<List<MovieDataModel>>(ApiException.NetworkError())
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.NetworkError() }
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

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
    fun `reduce load movie sections unknown error`() = runTest {
        val unknownErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.UnknownError()

        val appState = AppState.INITIAL
        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.UnknownError())
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.NetworkError() },
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

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