package com.tmdb.data.source.remote.implRetrofit.genre

import com.tmdb.api.model.genre.Genre
import com.tmdb.api.model.genre.GenresList
import com.tmdb.api.model.util.ApiException
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.implRetrofit.genre.GenreApi
import com.tmdb.data.model.GenreDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implRetrofit.model.ApiErrorImpl
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GenreRemoteDataSourceTest {
    private val genreApi = mock<GenreApi>()
    private val genreListApiModelToDataStateModelMapper: GenreListApiModelToDataStateModelMapper = mock()
    private val genreSource: GenreRemoteDataSource =
        GenreRemoteDataSourceImpl(genreApi, genreListApiModelToDataStateModelMapper)

    private val expectedApiGenreModel = Genre(id = 28, name = "Action")
    private val expectedGenreDataModel = GenreDataModel()

    private val expectedApiGenreList = GenresList(listOf(expectedApiGenreModel))
    private val expectedGenreDataModelList = listOf(expectedGenreDataModel)

    private val expectedSuccessApiResponse: ApiResponse<GenresList, NetworkErrorModel> =
        ApiResponse.Success(expectedApiGenreList)

    private val expectedNetworkException = ApiException.NetworkError(message = "Network error")
    private val expectedNetworkErrorApiResponse = ApiResponse.NetworkError(expectedNetworkException)

    private val expectedErrorBody = ApiErrorImpl()
    private val expectedErrorCode = 500
    private val expectedApiErrorException = Throwable("Body: [${expectedErrorBody}], code=${expectedErrorCode}")
    private val expectedApiErrorResponse = ApiResponse.ApiError(expectedErrorBody, expectedErrorCode)

    private val expectedUnknownException = Throwable("Unknown Exception")
    private val expectedUnknownErrorResponse = ApiResponse.UnknownError(expectedUnknownException)

    @Test
    fun `genre tv list success`() = runTest {
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.Success(expectedGenreDataModelList)

        whenever(genreListApiModelToDataStateModelMapper.map(expectedSuccessApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreTvList()).thenReturn(expectedSuccessApiResponse)

        genreSource.genreTvList().run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreTvList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedSuccessApiResponse)
    }

    @Test
    fun `genre tv list network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(genreListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreTvList()).thenReturn(expectedApiResponse)

        genreSource.genreTvList().run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreTvList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `genre tv list api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<GenresList, NetworkErrorModel> = expectedApiErrorResponse

        whenever(genreListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreTvList()).thenReturn(expectedApiResponse)

        genreSource.genreTvList().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreTvList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `genre tv list unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<GenresList, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(genreListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreTvList()).thenReturn(expectedApiResponse)

        genreSource.genreTvList().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreTvList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `genre movie list success`() = runTest {
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.Success(expectedGenreDataModelList)

        whenever(genreListApiModelToDataStateModelMapper.map(expectedSuccessApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreMovieList()).thenReturn(expectedSuccessApiResponse)

        genreSource.genreMovieList().run {
            assertTrue(this.isSuccess)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreMovieList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedSuccessApiResponse)
    }

    @Test
    fun `genre movie list network error`() = runTest {
        val expectedException = expectedNetworkException
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.NetworkError(expectedException)
        val expectedApiResponse = expectedNetworkErrorApiResponse

        whenever(genreListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreTvList()).thenReturn(expectedApiResponse)

        genreSource.genreTvList().run {
            assertTrue(this.isNetworkError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreTvList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `genre movie list api error`() = runTest {
        val expectedException = expectedApiErrorException
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<GenresList, NetworkErrorModel> = expectedApiErrorResponse

        whenever(genreListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreMovieList()).thenReturn(expectedApiResponse)

        genreSource.genreMovieList().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreMovieList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }

    @Test
    fun `genre movie list unknown error`() = runTest {
        val expectedException = expectedUnknownException
        val expectedDataState: DataState<List<GenreDataModel>> = DataState.Error(expectedException)
        val expectedApiResponse: ApiResponse<GenresList, NetworkErrorModel> = expectedUnknownErrorResponse

        whenever(genreListApiModelToDataStateModelMapper.map(expectedApiResponse)).thenReturn(expectedDataState)
        whenever(genreApi.genreMovieList()).thenReturn(expectedApiResponse)

        genreSource.genreMovieList().run {
            assertTrue(this.isError)
            assertSame(expected = expectedDataState, actual = this)
        }

        verify(genreApi, times(1)).genreMovieList()
        verify(genreListApiModelToDataStateModelMapper, times(1)).map(expectedApiResponse)
    }
}
