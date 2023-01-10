package com.tmdb_test.data.source.remote.impl_ktor.person

import com.tmdb_test.api.model.person.Person
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.api.impl_ktor.person.PersonApi
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