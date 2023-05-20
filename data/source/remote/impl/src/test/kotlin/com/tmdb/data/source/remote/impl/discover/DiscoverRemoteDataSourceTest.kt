package com.tmdb.data.source.remote.impl.discover

import com.tmdb.api.impl_retrofit.discover.DiscoverApi
import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.impl.util.model.ModelUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DiscoverRemoteDataSourceTest {
    private val discoverApi = mock<DiscoverApi>()
    private val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(discoverApi)

    @Test
    fun `discover movie list success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        whenever(discoverApi.discoverMovie()).thenReturn(response)
        discoverSource.discoverMovie().run { assertSame(response, this) }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list network error`() = runTest {
        whenever(discoverApi.discoverMovie()).thenReturn(ApiResponse.NetworkError())
        discoverSource.discoverMovie().run { assertTrue(this.isNetworkError) }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list api error`() = runTest {
        whenever(discoverApi.discoverMovie()).thenReturn(ApiResponse.ApiError())
        discoverSource.discoverMovie().run { assertTrue(this.isApiError) }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list unknown error`() = runTest {
        whenever(discoverApi.discoverMovie()).thenReturn(ApiResponse.UnknownError())
        discoverSource.discoverMovie().run { assertTrue(this.isUnknownError) }
        verify(discoverApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover tv list success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        whenever(discoverApi.discoverTv()).thenReturn(response)
        discoverSource.discoverTv().run { assertSame(this, response) }
        verify(discoverApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list network error`() = runTest {
        whenever(discoverApi.discoverTv()).thenReturn(ApiResponse.NetworkError())
        discoverSource.discoverTv().run { assertTrue(this.isNetworkError) }
        verify(discoverApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list api error`() = runTest {
        whenever(discoverApi.discoverTv()).thenReturn(ApiResponse.ApiError())
        discoverSource.discoverTv().run { assertTrue(this.isApiError) }
        verify(discoverApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list unknown error`() = runTest {
        whenever(discoverApi.discoverTv()).thenReturn(ApiResponse.UnknownError())
        discoverSource.discoverTv().run { assertTrue(this.isUnknownError) }
        verify(discoverApi, times(1)).discoverTv()
    }
}
