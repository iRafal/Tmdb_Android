package com.tmdb.data.source.remote.implKtor.movie

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiException
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.implKtor.movie.MovieApi
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.util.model.ApiErrorImpl
import com.tmdb.data.source.remote.implKtor.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieRemoteDataSourceTest {
    private val movieApi = mock<MovieApi>()
    private val movieApiModelToDataStateModelMapper: MovieApiModelToDataStateModelMapper = mock()
    private val moviesListApiModelToDataStateModelMapper: MoviesListApiModelToDataStateModelMapper = mock()
    private val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(
        movieApi,
        movieApiModelToDataStateModelMapper,
        moviesListApiModelToDataStateModelMapper
    )

    private val expectedNetworkException = ApiException.NetworkError(message = "Network error")
    private val expectedNetworkErrorApiResponse = ApiResponse.NetworkError(expectedNetworkException)

    private val expectedErrorBody = ApiErrorImpl()
    private val expectedErrorCode = 500
    private val expectedApiErrorException = Throwable("Body: [$expectedErrorBody], code=$expectedErrorCode")
    private val expectedApiErrorResponse = ApiResponse.ApiError(expectedErrorBody, expectedErrorCode)

    private val expectedUnknownException = Throwable("Unknown Exception")
    private val expectedUnknownErrorResponse = ApiResponse.UnknownError(expectedUnknownException)

    private val expectedMovieId = 550

    @Test
    fun `movie success`() = runTest {
        val expectedApiResponse = ApiResponse.Success(ModelUtil.movieModel)
        val expectedDataState = DataState.Success(ModelUtil.movieDataModel)

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.movie(expectedMovieId)).thenReturn(expectedApiResponse)

        movieSource.movie(expectedMovieId).run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).movie(expectedMovieId)
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `movie network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<MovieDataModel> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.movie(expectedMovieId)).thenReturn(expectedApiResponse)

        movieSource.movie(expectedMovieId).run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).movie(expectedMovieId)
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `movie api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<MovieDataModel> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<Movie, NetworkErrorModel> = expectedApiErrorResponse

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.movie(expectedMovieId)).thenReturn(expectedApiResponse)

        movieSource.movie(expectedMovieId).run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).movie(expectedMovieId)
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `movie unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<MovieDataModel> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<Movie, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.movie(expectedMovieId)).thenReturn(expectedApiResponse)

        movieSource.movie(expectedMovieId).run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).movie(expectedMovieId)
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `latest movie success`() = runTest {
        val expectedApiResponse = ApiResponse.Success(ModelUtil.movieModel)
        val expectedDataState = DataState.Success(ModelUtil.movieDataModel)

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.latestMovie()).thenReturn(expectedApiResponse)

        movieSource.latestMovie().run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).latestMovie()
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `latest movie network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<MovieDataModel> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.latestMovie()).thenReturn(expectedApiResponse)

        movieSource.latestMovie().run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).latestMovie()
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `latest movie api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<MovieDataModel> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<Movie, NetworkErrorModel> = expectedApiErrorResponse

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.latestMovie()).thenReturn(expectedApiResponse)

        movieSource.latestMovie().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).latestMovie()
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `latest movie unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<MovieDataModel> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<Movie, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(movieApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.latestMovie()).thenReturn(expectedApiResponse)

        movieSource.latestMovie().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).latestMovie()
        verify(movieApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now playing movies success`() = runTest {
        val expectedApiResponse = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        val expectedDataState = DataState.Success(listOf(ModelUtil.movieDataModel))

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.nowPlayingMovies()).thenReturn(expectedApiResponse)

        movieSource.nowPlayingMovies().run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).nowPlayingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now playing movies network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(movieApi.nowPlayingMovies()).thenReturn(expectedApiResponse)
        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)

        movieSource.nowPlayingMovies().run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).nowPlayingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now playing movies api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedApiErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.nowPlayingMovies()).thenReturn(expectedApiResponse)

        movieSource.nowPlayingMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).nowPlayingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now playing movies unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.nowPlayingMovies()).thenReturn(expectedApiResponse)

        movieSource.nowPlayingMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).nowPlayingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now popular movies success`() = runTest {
        val expectedApiResponse = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        val expectedDataState = DataState.Success(listOf(ModelUtil.movieDataModel))

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.nowPopularMovies()).thenReturn(expectedApiResponse)

        movieSource.nowPopularMovies().run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).nowPopularMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now popular movies network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.nowPopularMovies()).thenReturn(expectedApiResponse)

        movieSource.nowPopularMovies().run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).nowPopularMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now popular movies api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedApiErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.nowPopularMovies()).thenReturn(expectedApiResponse)

        movieSource.nowPopularMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }
        verify(movieApi, times(1)).nowPopularMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `now popular movies unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.nowPopularMovies()).thenReturn(expectedApiResponse)

        movieSource.nowPopularMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).nowPopularMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `top rated movies success`() = runTest {
        val expectedApiResponse = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        val expectedDataState = DataState.Success(listOf(ModelUtil.movieDataModel))

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.topRatedMovies()).thenReturn(expectedApiResponse)

        movieSource.topRatedMovies().run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).topRatedMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `top rated movies network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.topRatedMovies()).thenReturn(expectedApiResponse)

        movieSource.topRatedMovies().run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).topRatedMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `top rated movies api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedApiErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.topRatedMovies()).thenReturn(expectedApiResponse)

        movieSource.topRatedMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }
        verify(movieApi, times(1)).topRatedMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `top rated movies unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.topRatedMovies()).thenReturn(expectedApiResponse)

        movieSource.topRatedMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).topRatedMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `upcoming movies success`() = runTest {
        val expectedApiResponse = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        val expectedDataState = DataState.Success(listOf(ModelUtil.movieDataModel))

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.upcomingMovies()).thenReturn(expectedApiResponse)

        movieSource.upcomingMovies().run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).upcomingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `upcoming movies network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.upcomingMovies()).thenReturn(expectedApiResponse)

        movieSource.upcomingMovies().run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).upcomingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `upcoming movies api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedApiErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.upcomingMovies()).thenReturn(expectedApiResponse)

        movieSource.upcomingMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).upcomingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `upcoming movies unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<List<MovieDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<DataPage<Movie>, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(moviesListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(movieApi.upcomingMovies()).thenReturn(expectedApiResponse)

        movieSource.upcomingMovies().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(movieApi, times(1)).upcomingMovies()
        verify(moviesListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }
}
