package com.tmdb_test.data.source.remote.discover

import com.tmdb_test.data.api.ModelUtil
import com.tmdb_test.data.api.impl_retrofit.discover.DiscoverApi
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.util.ApiResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DiscoverRemoteDataSourceTest {
    private val discoverApi = mock<DiscoverApi>()
    private val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(discoverApi)

    @Test
    fun `discover movie list success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        `when`(discoverApi.discoverMovie()).thenReturn(response)
        discoverSource.discoverMovie().also { result ->
            Assert.assertSame(response, result)
        }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list network error`() = runBlocking<Unit> {
        `when`(discoverApi.discoverMovie()).thenReturn(ApiResponse.NetworkError())
        discoverSource.discoverMovie().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list api error`() = runBlocking<Unit> {
        `when`(discoverApi.discoverMovie()).thenReturn(ApiResponse.ApiError())
        discoverSource.discoverMovie().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list unknown error`() = runBlocking<Unit> {
        `when`(discoverApi.discoverMovie()).thenReturn(ApiResponse.UnknownError())
        discoverSource.discoverMovie().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover tv list success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        `when`(discoverApi.discoverTv()).thenReturn(response)
        discoverSource.discoverTv().also { result ->
            Assert.assertSame(result, response)
        }
        verify(discoverApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list network error`() = runBlocking<Unit> {
        `when`(discoverApi.discoverTv()).thenReturn(ApiResponse.NetworkError())
        discoverSource.discoverTv().also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(discoverApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list api error`() = runBlocking<Unit> {
        `when`(discoverApi.discoverTv()).thenReturn(ApiResponse.ApiError())
        discoverSource.discoverTv().also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(discoverApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list unknown error`() = runBlocking<Unit> {
        `when`(discoverApi.discoverTv()).thenReturn(ApiResponse.UnknownError())
        discoverSource.discoverTv().also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(discoverApi, times(1)).discoverTv()
    }
}