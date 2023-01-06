package com.tmdb_test.data.source.remote.impl.movie

import com.tmdb_test.api.impl_retrofit.movie.MovieApi
import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.impl.util.model.ModelUtil
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
class MovieRemoteDataSourceTest {
    private val movieApi = mock<MovieApi>()
    private val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(movieApi)

    private val movieId = 550

    @Test
    fun `movie success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.movieModel)
        whenever(movieApi.movie(movieId)).thenReturn(response)
        movieSource.movie(movieId).run { assertSame(this, response) }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie network error`() = runTest {
        whenever(movieApi.movie(movieId)).thenReturn(ApiResponse.NetworkError())
        movieSource.movie(movieId).run { assertTrue(this.isNetworkError) }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie api error`() = runTest {
        whenever(movieApi.movie(movieId)).thenReturn(ApiResponse.ApiError())
        movieSource.movie(movieId).run { assertTrue(this.isApiError) }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie unknown error`() = runTest {
        whenever(movieApi.movie(movieId)).thenReturn(ApiResponse.UnknownError())
        movieSource.movie(movieId).run { assertTrue(this.isUnknownError) }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `latest movie success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.movieModel)
        whenever(movieApi.latestMovie()).thenReturn(response)
        movieSource.latestMovie().run { assertSame(this, response) }
        verify(movieApi, times(1)).latestMovie()
    }

    @Test
    fun `latest movie network error`() = runTest {
        whenever(movieApi.latestMovie()).thenReturn(ApiResponse.NetworkError())
        movieSource.latestMovie().run { assertTrue(this.isNetworkError) }
        verify(movieApi, times(1)).latestMovie()
    }

    @Test
    fun `latest movie api error`() = runTest {
        whenever(movieApi.latestMovie()).thenReturn(ApiResponse.ApiError())
        movieSource.latestMovie().run { assertTrue(this.isApiError) }
        verify(movieApi, times(1)).latestMovie()
    }

    @Test
    fun `now playing movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        whenever(movieApi.nowPlayingMovies()).thenReturn(response)
        movieSource.nowPlayingMovies().run { assertSame(this, response) }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies network error`() = runTest {
        whenever(movieApi.nowPlayingMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.nowPlayingMovies().run { assertTrue(this.isNetworkError) }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies api error`() = runTest {
        whenever(movieApi.nowPlayingMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.nowPlayingMovies().run { assertTrue(this.isApiError) }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies unknown error`() = runTest {
        whenever(movieApi.nowPlayingMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.nowPlayingMovies().run { assertTrue(this.isUnknownError) }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now popular movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        whenever(movieApi.nowPopularMovies()).thenReturn(response)
        movieSource.nowPopularMovies().run { assertSame(this, response) }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies network error`() = runTest {
        whenever(movieApi.nowPopularMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.nowPopularMovies().run { assertTrue(this.isNetworkError) }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies api error`() = runTest {
        whenever(movieApi.nowPopularMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.nowPopularMovies().run { assertTrue(this.isApiError) }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies unknown error`() = runTest {
        whenever(movieApi.nowPopularMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.nowPopularMovies().run { assertTrue(this.isUnknownError) }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `top rated movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        whenever(movieApi.topRatedMovies()).thenReturn(response)
        movieSource.topRatedMovies().run { assertSame(this, response) }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies network error`() = runTest {
        whenever(movieApi.topRatedMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.topRatedMovies().run { assertTrue(this.isNetworkError) }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies api error`() = runTest {
        whenever(movieApi.topRatedMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.topRatedMovies().run { assertTrue(this.isApiError) }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies unknown error`() = runTest {
        whenever(movieApi.topRatedMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.topRatedMovies().run { assertTrue(this.isUnknownError) }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `upcoming movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        whenever(movieApi.upcomingMovies()).thenReturn(response)
        movieSource.upcomingMovies().run { assertSame(this, response) }
        verify(movieApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies network error`() = runTest {
        whenever(movieApi.upcomingMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.upcomingMovies().run { assertTrue(this.isNetworkError) }
        verify(movieApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies api error`() = runTest {
        whenever(movieApi.upcomingMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.upcomingMovies().run { assertTrue(this.isApiError) }
        verify(movieApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies unknown error`() = runTest {
        whenever(movieApi.upcomingMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.upcomingMovies().run { assertTrue(this.isUnknownError) }
        verify(movieApi, times(1)).upcomingMovies()
    }
}