package com.tmdb_test.data.source.remote.genre

import com.tmdb_test.data.api.impl_retrofit.genre.GenreApi
import com.tmdb_test.data.api.model.genre.Genre
import com.tmdb_test.data.api.model.genre.GenresList
import com.tmdb_test.data.api.util.ApiResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GenreRemoteDataSourceTest {
    private val genreApi = mock<GenreApi>()
    private val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(genreApi)

    @Test
    fun `genre tv list success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            GenresList(listOf(Genre(id = 28, name = "Action")))
        )
        `when`(genreApi.genreTvList()).thenReturn(response)
        genreSource.genreTvList().also { result ->
            Assert.assertSame(result, response)
        }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list network error`() = runBlocking<Unit> {
        `when`(genreApi.genreTvList()).thenReturn(ApiResponse.NetworkError())
        genreSource.genreTvList().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list api error`() = runBlocking<Unit> {
        `when`(genreApi.genreTvList()).thenReturn(ApiResponse.ApiError())
        genreSource.genreTvList().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list unknown error`() = runBlocking<Unit> {
        `when`(genreApi.genreTvList()).thenReturn(ApiResponse.UnknownError())
        genreSource.genreTvList().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre movie list success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            GenresList(listOf(Genre(id = 28, name = "Action")))
        )
        `when`(genreApi.genreMovieList()).thenReturn(response)
        genreSource.genreMovieList().also { result ->
            Assert.assertSame(result, response)
        }
        verify(genreApi, times(1)).genreMovieList()
    }

    @Test
    fun `genre movie list network error`() = runBlocking<Unit> {
        `when`(genreApi.genreTvList()).thenReturn(ApiResponse.NetworkError())
        genreSource.genreTvList().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre movie list api error`() = runBlocking<Unit> {
        `when`(genreApi.genreMovieList()).thenReturn(ApiResponse.ApiError())
        genreSource.genreMovieList().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(genreApi, times(1)).genreMovieList()
    }

    @Test
    fun `genre movie list unknown error`() = runBlocking<Unit> {
        `when`(genreApi.genreMovieList()).thenReturn(ApiResponse.UnknownError())
        genreSource.genreMovieList().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(genreApi, times(1)).genreMovieList()
    }
}