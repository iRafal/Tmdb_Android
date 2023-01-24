package com.tmdb_test.store.reducer

import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.ApiException
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.model.state.DataState
import com.tmdb_test.data.model.movie.MovieDataModel
import com.tmdb_test.data.source.remote.contract.MovieLocalDataSource
import com.tmdb_test.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb_test.store.action.home.HomeAction
import com.tmdb_test.store.reducer.home.HomeFeatureEffects
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import com.tmdb_test.store.reducer.home.HomeFeatureSliceImpl
import com.tmdb_test.store.reducer.util.ModelUtil
import com.tmdb_test.store.state.app.AppState
import com.tmdb_test.store.state.FeatureState
import com.tmdb_test.store.state.FeatureState.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

    private val discoverRemoteSource = mock<DiscoverRemoteDataSource>()
    private val genreRemoteSource = mock<GenreRemoteDataSource>()
    private val movieRemoteSource = mock<MovieRemoteDataSource>()
    private val personRemoteSource = mock<PersonRemoteDataSource>()
    private val movieLocalSource = mock<MovieLocalDataSource>()

    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

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
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper =  { dataSuccessMovies },
            moviesDataToFeatureStateMapper = { Success(movies) },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieRemoteSource.nowPlayingMovies()).thenReturn(successResult)
        whenever(movieRemoteSource.nowPopularMovies()).thenReturn(successResult)
        whenever(movieRemoteSource.topRatedMovies()).thenReturn(successResult)
        whenever(movieRemoteSource.upcomingMovies()).thenReturn(successResult)

        val executor = createMockEffectExecutor(
            discoverRemoteSource,
            genreRemoteSource,
            movieRemoteSource,
            personRemoteSource,
            movieLocalSource
        )
        effect?.invoke(executor)

        verify(movieRemoteSource, times(1)).nowPlayingMovies()
        verify(movieRemoteSource, times(1)).nowPopularMovies()
        verify(movieRemoteSource, times(1)).topRatedMovies()
        verify(movieRemoteSource, times(1)).upcomingMovies()
        
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
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.Error() },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieRemoteSource.nowPlayingMovies()).thenReturn(apiErrorResult)
        whenever(movieRemoteSource.nowPopularMovies()).thenReturn(apiErrorResult)
        whenever(movieRemoteSource.topRatedMovies()).thenReturn(apiErrorResult)
        whenever(movieRemoteSource.upcomingMovies()).thenReturn(apiErrorResult)

        val executor = createMockEffectExecutor(
            discoverRemoteSource,
            genreRemoteSource,
            movieRemoteSource,
            personRemoteSource,
            movieLocalSource
        )
        effect?.invoke(executor)

        verify(movieRemoteSource, times(1)).nowPlayingMovies()
        verify(movieRemoteSource, times(1)).nowPopularMovies()
        verify(movieRemoteSource, times(1)).topRatedMovies()
        verify(movieRemoteSource, times(1)).upcomingMovies()

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
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.NetworkError() },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieRemoteSource.nowPlayingMovies()).thenReturn(networkErrorResult)
        whenever(movieRemoteSource.nowPopularMovies()).thenReturn(networkErrorResult)
        whenever(movieRemoteSource.topRatedMovies()).thenReturn(networkErrorResult)
        whenever(movieRemoteSource.upcomingMovies()).thenReturn(networkErrorResult)

        val executor = createMockEffectExecutor(
            discoverRemoteSource,
            genreRemoteSource,
            movieRemoteSource,
            personRemoteSource,
            movieLocalSource
        )
        effect?.invoke(executor)

        verify(movieRemoteSource, times(1)).nowPlayingMovies()
        verify(movieRemoteSource, times(1)).nowPopularMovies()
        verify(movieRemoteSource, times(1)).topRatedMovies()
        verify(movieRemoteSource, times(1)).upcomingMovies()

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
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.NetworkError() },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        whenever(movieRemoteSource.nowPlayingMovies()).thenReturn(unknownErrorResult)
        whenever(movieRemoteSource.nowPopularMovies()).thenReturn(unknownErrorResult)
        whenever(movieRemoteSource.topRatedMovies()).thenReturn(unknownErrorResult)
        whenever(movieRemoteSource.upcomingMovies()).thenReturn(unknownErrorResult)

        val executor = createMockEffectExecutor(
            discoverRemoteSource,
            genreRemoteSource,
            movieRemoteSource,
            personRemoteSource,
            movieLocalSource
        )
        effect?.invoke(executor)

        verify(movieRemoteSource, times(1)).nowPlayingMovies()
        verify(movieRemoteSource, times(1)).nowPopularMovies()
        verify(movieRemoteSource, times(1)).topRatedMovies()
        verify(movieRemoteSource, times(1)).upcomingMovies()

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