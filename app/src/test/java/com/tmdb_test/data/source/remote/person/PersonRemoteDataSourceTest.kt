package com.tmdb_test.data.source.remote.person

import com.tmdb_test.data.api.ModelUtil
import com.tmdb_test.data.api.impl_retrofit.person.PersonApi
import com.tmdb_test.data.api.util.ApiResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PersonRemoteDataSourceTest {
    private val personApi = mock<PersonApi>()
    private val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(personApi)

    private val personId = 287

    @Test
    fun `person details success`() = runBlocking<Unit> {
        val response = ApiResponse.Success(ModelUtil.personModel)
        `when`(personApi.personDetails(personId)).thenReturn(response)
        personSource.personDetails(personId).also { result ->
            Assert.assertSame(result, response)
        }
        verify(personApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details network error`() = runBlocking<Unit> {
        `when`(personApi.personDetails(personId)).thenReturn(ApiResponse.NetworkError())
        personSource.personDetails(personId).also { result ->
            Assert.assertTrue(result.isNetworkError)
        }
        verify(personApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details api error`() = runBlocking<Unit> {
        `when`(personApi.personDetails(personId)).thenReturn(ApiResponse.ApiError())
        personSource.personDetails(personId).also { result ->
            Assert.assertTrue(result.isApiError)
        }
        verify(personApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details unknown error`() = runBlocking<Unit> {
        `when`(personApi.personDetails(personId)).thenReturn(ApiResponse.UnknownError())
        personSource.personDetails(personId).also { result ->
            Assert.assertTrue(result.isUnknownError)
        }
        verify(personApi, times(1)).personDetails(personId)
    }
}