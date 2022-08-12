package com.tmdb_test.data.source.remote.person

import com.tmdb_test.data.api.impl_retrofit.NetworkErrorModel
import com.tmdb_test.data.api.model.person.Person
import com.tmdb_test.data.api.util.ApiResponse


interface PersonRemoteDataSource {
    suspend fun personDetails(
        personId: Int,
        language: String? = null,
        appendToResponse: String? = null,
    ): ApiResponse<Person, NetworkErrorModel>
}