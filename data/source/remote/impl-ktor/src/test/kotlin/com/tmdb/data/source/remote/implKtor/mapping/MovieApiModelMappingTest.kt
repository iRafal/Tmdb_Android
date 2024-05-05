package com.tmdb.data.source.remote.implKtor.mapping

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiException
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.remote.implKtor.util.model.ApiErrorImpl
import com.tmdb.data.source.remote.implKtor.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MovieApiModelMappingTest {
    private val baseUrl = "https://web.page.com"
    private val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(baseUrl)
    private val movieApiModelToDataModelMapper: MovieApiModelToDataModelMapper =
        MovieApiModelToDataModelMapperImpl(imageUrlProvider)
    private val moviesListApiModelToDataStateModelMapper: MoviesListApiModelToDataStateModelMapper =
        MoviesListApiModelToDataStateModelMapperImpl(movieApiModelToDataModelMapper)

    @Test
    fun testMovieApiModelToDataModelMapper() {
        val input = ModelUtil.movieModel
        val actual = movieApiModelToDataModelMapper.map(input)

        assertEquals(expected = ModelUtil.movieDataModel, actual = actual)
    }

    @Test
    fun `mapping Success ApiResponse_Success to DataState_Success`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> =
            ApiResponse.Success(DataPage(page = 0, results = listOf(ModelUtil.movieModel)))
        val actual = moviesListApiModelToDataStateModelMapper.map(input)
        val expected = DataState.Success(listOf(ModelUtil.movieDataModel))

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `mapping Success ApiResponse_ApiError to DataState_Error`() {
        val expectedErrorBody = ApiErrorImpl()
        val expectedErrorCode = 500
        val expectedException = ApiException.InternalServerError()

        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> =
            ApiResponse.ApiError(expectedErrorBody, expectedErrorCode, expectedException)
        val actual = moviesListApiModelToDataStateModelMapper.map(input)

        assertTrue(actual.isError)
    }

    @Test
    fun `mapping Success ApiResponse_NetworkError to DataState_NetworkError`() {
        val causeException = ApiException.NetworkError()
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.NetworkError(causeException)
        val actual = moviesListApiModelToDataStateModelMapper.map(input)
        val expected = DataState.NetworkError<List<MovieDataModel>>(causeException)

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `mapping Success ApiResponse_UnknownError to DataState_Error`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.UnknownError()
        val actual = moviesListApiModelToDataStateModelMapper.map(input)
        val expected = DataState.Error<List<MovieDataModel>>(null)

        assertEquals(expected = expected, actual = actual)
    }
}
