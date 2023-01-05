package com.tmdb_test.data.source.remote.person

import com.tmdb_test.api.impl_retrofit.util.ApiResponse
import com.tmdb_test.api.impl_retrofit.util.NetworkErrorModel
import com.tmdb_test.api.model.person.Person


interface PersonRemoteDataSource {
    suspend fun personDetails(
        personId: Int,
        language: String? = null,
        appendToResponse: String? = null,
    ): ApiResponse<Person, NetworkErrorModel>
}