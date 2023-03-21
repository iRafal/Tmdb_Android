package com.tmdb.data.source.remote.impl.person

import com.tmdb.api.impl_retrofit.person.PersonApi
import com.tmdb.api.model.person.Person
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
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