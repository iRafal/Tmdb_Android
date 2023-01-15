package com.tmdb_test.data.model.mapping.movie

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.ApiException
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.model.DataState
import com.tmdb_test.data.model.MovieDataModel
import com.tmdb_test.data.model.util.ModelUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesApiToDataStateMapperTest {
    private val baseUrl = "https://web.site.com"
    private val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(baseUrl)
    private val itemMapper: MovieApiToDataModelMapper = movieApiToDataModelMapperImpl(imageUrlProvider)
    private val listMapper: MoviesApiToDataStateMapper = moviesApiToDataStateMapperImpl(itemMapper)

    @Test
    fun `mapping Success ApiResponse_Success to DataState_Success`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> =
            ApiResponse.Success(DataPage(page = 0, results = listOf(ModelUtil.movieModel)))

        val actual = listMapper.invoke(input)
        val expected = DataState.Success(listOf(ModelUtil.movieDataModel))
        assertEquals(expected, actual)
    }

    @Test
    fun `mapping Success ApiResponse_ApiError to DataState_Error`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.ApiError()
        val actual = listMapper.invoke(input)
        val expected = DataState.Error<List<MovieDataModel>>(null)
        assertEquals(expected, actual)
    }

    @Test
    fun `mapping Success ApiResponse_NetworkError to DataState_NetworkError`() {
        val causeException = ApiException.NetworkError()
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.NetworkError(causeException)
        val actual = listMapper.invoke(input)
        val expected = DataState.NetworkError<List<MovieDataModel>>(causeException)
        assertEquals(expected, actual)
    }

    @Test
    fun `mapping Success ApiResponse_UnknownError to DataState_Error`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.UnknownError()
        val actual = listMapper.invoke(input)
        val expected = DataState.Error<List<MovieDataModel>>(null)
        assertEquals(expected, actual)
    }
}