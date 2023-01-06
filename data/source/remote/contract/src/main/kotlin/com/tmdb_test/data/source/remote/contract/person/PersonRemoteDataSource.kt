package com.tmdb_test.data.source.remote.contract.person

import com.tmdb_test.api.model.person.Person
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel


interface PersonRemoteDataSource {
    suspend fun personDetails(
        personId: Int,
        language: String? = null,
        appendToResponse: String? = null,
    ): ApiResponse<Person, NetworkErrorModel>
}