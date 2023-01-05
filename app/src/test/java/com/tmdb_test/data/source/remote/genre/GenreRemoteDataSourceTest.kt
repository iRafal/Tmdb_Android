package com.tmdb_test.data.source.remote.genre

import com.tmdb_test.api.model.genre.Genre
import com.tmdb_test.api.model.genre.GenresList
import com.tmdb_test.data.api.impl_retrofit.genre.GenreApi
import com.tmdb_test.data.api.util.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GenreRemoteDataSourceTest {
    private val genreApi = mock<GenreApi>()
    private val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(genreApi)

    @Test
    fun `genre tv list success`() = runTest {
        val response = ApiResponse.Success(
            GenresList(listOf(Genre(id = 28, name = "Action")))
        )
        whenever(genreApi.genreTvList()).thenReturn(response)
        genreSource.genreTvList().run { assertSame(this, response) }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list network error`() = runTest {
        whenever(genreApi.genreTvList()).thenReturn(ApiResponse.NetworkError())
        genreSource.genreTvList().run { assertTrue(this.isNetworkError) }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list api error`() = runTest {
        whenever(genreApi.genreTvList()).thenReturn(ApiResponse.ApiError())
        genreSource.genreTvList().run { assertTrue(this.isApiError) }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list unknown error`() = runTest {
        whenever(genreApi.genreTvList()).thenReturn(ApiResponse.UnknownError())
        genreSource.genreTvList().run { assertTrue(this.isUnknownError) }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre movie list success`() = runTest {
        val response = ApiResponse.Success(
            GenresList(listOf(Genre(id = 28, name = "Action")))
        )
        whenever(genreApi.genreMovieList()).thenReturn(response)
        genreSource.genreMovieList().run { assertSame(this, response) }
        verify(genreApi, times(1)).genreMovieList()
    }

    @Test
    fun `genre movie list network error`() = runTest {
        whenever(genreApi.genreTvList()).thenReturn(ApiResponse.NetworkError())
        genreSource.genreTvList().run { assertTrue(this.isNetworkError) }
        verify(genreApi, times(1)).genreTvList()
    }

    @Test
    fun `genre movie list api error`() = runTest {
        whenever(genreApi.genreMovieList()).thenReturn(ApiResponse.ApiError())
        genreSource.genreMovieList().run { assertTrue(this.isApiError) }
        verify(genreApi, times(1)).genreMovieList()
    }

    @Test
    fun `genre movie list unknown error`() = runTest {
        whenever(genreApi.genreMovieList()).thenReturn(ApiResponse.UnknownError())
        genreSource.genreMovieList().run { assertTrue(this.isUnknownError) }
        verify(genreApi, times(1)).genreMovieList()
    }
}