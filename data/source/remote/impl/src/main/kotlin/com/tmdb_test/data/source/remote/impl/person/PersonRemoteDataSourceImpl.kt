package com.tmdb_test.data.source.remote.impl.person

import com.tmdb_test.api.impl_retrofit.person.PersonApi
import com.tmdb_test.api.model.person.Person
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel
import com.tmdb_test.data.source.remote.contract.person.PersonRemoteDataSource
import javax.inject.Inject


class PersonRemoteDataSourceImpl @Inject constructor(
    private val api: PersonApi
) : PersonRemoteDataSource {
    override suspend fun personDetails(
        personId: Int,
        language: String?,
        appendToResponse: String?
    ): ApiResponse<Person, NetworkErrorModel> =
        api.personDetails(personId, language, appendToResponse)
}