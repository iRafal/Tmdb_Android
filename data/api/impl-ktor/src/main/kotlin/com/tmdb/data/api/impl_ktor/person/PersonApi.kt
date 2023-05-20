package com.tmdb.data.api.impl_ktor.person

import com.tmdb.api.model.person.Person
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel

interface PersonApi {
    suspend fun personDetails(
        personId: Int,
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        language: String? = null,
        appendToResponse: String? = null
    ): ApiResponse<Person, NetworkErrorModel>
}
