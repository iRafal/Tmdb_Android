package com.tmdb_test.data.source.remote.person

import com.tmdb_test.api.impl_retrofit.person.PersonApi
import com.tmdb_test.api.impl_retrofit.util.ApiResponse
import com.tmdb_test.api.impl_retrofit.util.NetworkErrorModel
import com.tmdb_test.api.model.person.Person
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