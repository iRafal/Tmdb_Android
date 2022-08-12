package com.tmdb_test.data.source.remote.movie

import com.tmdb_test.data.api.ModelUtil
import com.tmdb_test.data.api.impl_retrofit.movie.MovieApi
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.util.ApiResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MovieRemoteDataSourceTest {
    private val movieApi = mock<MovieApi>()
    private val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(movieApi)

    private val movieId = 550

    @Test
    fun `movie success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(ModelUtil.movieModel)
        `when`(movieApi.movie(movieId)).thenReturn(response)
        movieSource.movie(movieId).also { result ->
            Assert.assertSame(result, response)
        }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie network error`() = runBlocking<Unit> {
        `when`(movieApi.movie(movieId)).thenReturn(ApiResponse.NetworkError())
        movieSource.movie(movieId).also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie api error`() = runBlocking<Unit> {
        `when`(movieApi.movie(movieId)).thenReturn(ApiResponse.ApiError())
        movieSource.movie(movieId).also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie unknown error`() = runBlocking<Unit> {
        `when`(movieApi.movie(movieId)).thenReturn(ApiResponse.UnknownError())
        movieSource.movie(movieId).also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(movieApi, times(1)).movie(movieId)
    }

    @Test
    fun `latest movie success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            ModelUtil.movieModel
        )
        `when`(movieApi.latestMovie()).thenReturn(response)
        movieSource.latestMovie().also { result ->
            Assert.assertSame(result, response)
        }
        verify(movieApi, times(1)).latestMovie()
    }

    @Test
    fun `latest movie network error`() = runBlocking<Unit> {
        `when`(movieApi.latestMovie()).thenReturn(ApiResponse.NetworkError())
        movieSource.latestMovie().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(movieApi, times(1)).latestMovie()
    }

    @Test
    fun `latest movie api error`() = runBlocking<Unit> {
        `when`(movieApi.latestMovie()).thenReturn(ApiResponse.ApiError())
        movieSource.latestMovie().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(movieApi, times(1)).latestMovie()
    }

    @Test
    fun `now playing movies success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        `when`(movieApi.nowPlayingMovies()).thenReturn(response)
        movieSource.nowPlayingMovies().also { result ->
            Assert.assertSame(result, response)
        }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies network error`() = runBlocking<Unit> {
        `when`(movieApi.nowPlayingMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.nowPlayingMovies().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies api error`() = runBlocking<Unit> {
        `when`(movieApi.nowPlayingMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.nowPlayingMovies().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies unknown error`() = runBlocking<Unit> {
        `when`(movieApi.nowPlayingMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.nowPlayingMovies().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(movieApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now popular movies success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        `when`(movieApi.nowPopularMovies()).thenReturn(response)
        movieSource.nowPopularMovies().also { result ->
            Assert.assertSame(result, response)
        }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies network error`() = runBlocking<Unit> {
        `when`(movieApi.nowPopularMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.nowPopularMovies().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies api error`() = runBlocking<Unit> {
        `when`(movieApi.nowPopularMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.nowPopularMovies().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies unknown error`() = runBlocking<Unit> {
        `when`(movieApi.nowPopularMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.nowPopularMovies().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(movieApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `top rated movies success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        `when`(movieApi.topRatedMovies()).thenReturn(response)
        movieSource.topRatedMovies().also { result ->
            Assert.assertSame(result, response)
        }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies network error`() = runBlocking<Unit> {
        `when`(movieApi.topRatedMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.topRatedMovies().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies api error`() = runBlocking<Unit> {
        `when`(movieApi.topRatedMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.topRatedMovies().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies unknown error`() = runBlocking<Unit> {
        `when`(movieApi.topRatedMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.topRatedMovies().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(movieApi, times(1)).topRatedMovies()
    }

    @Test
    fun `upcoming movies success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        `when`(movieApi.upcomingMovies()).thenReturn(response)
        movieSource.upcomingMovies().also { result ->
            Assert.assertSame(result, response)
        }
        verify(movieApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies network error`() = runBlocking<Unit> {
        `when`(movieApi.upcomingMovies()).thenReturn(ApiResponse.NetworkError())
        movieSource.upcomingMovies().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(movieApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies api error`() = runBlocking<Unit> {
        `when`(movieApi.upcomingMovies()).thenReturn(ApiResponse.ApiError())
        movieSource.upcomingMovies().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(movieApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies unknown error`() = runBlocking<Unit> {
        `when`(movieApi.upcomingMovies()).thenReturn(ApiResponse.UnknownError())
        movieSource.upcomingMovies().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(movieApi, times(1)).upcomingMovies()
    }
}