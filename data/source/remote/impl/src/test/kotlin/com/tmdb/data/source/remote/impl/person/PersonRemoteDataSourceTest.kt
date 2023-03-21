package com.tmdb.data.source.remote.impl.person

import com.tmdb.api.impl_retrofit.person.PersonApi
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.impl.util.model.ModelUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PersonRemoteDataSourceTest {
    private val personApi = mock<PersonApi>()
    private val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(personApi)

    private val personId = 287

    @Test
    fun `person details success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.personModel)
        whenever(personApi.personDetails(personId)).thenReturn(response)
        personSource.personDetails(personId).run { assertSame(this, response) }
        verify(personApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details network error`() = runTest {
        whenever(personApi.personDetails(personId)).thenReturn(ApiResponse.NetworkError())
        personSource.personDetails(personId).run { assertTrue(this.isNetworkError) }
        verify(personApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details api error`() = runTest {
        whenever(personApi.personDetails(personId)).thenReturn(ApiResponse.ApiError())
        personSource.personDetails(personId).run { assertTrue(this.isApiError) }
        verify(personApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details unknown error`() = runTest {
        whenever(personApi.personDetails(personId)).thenReturn(ApiResponse.UnknownError())
        personSource.personDetails(personId).run { assertTrue(this.isUnknownError) }
        verify(personApi, times(1)).personDetails(personId)
    }
}