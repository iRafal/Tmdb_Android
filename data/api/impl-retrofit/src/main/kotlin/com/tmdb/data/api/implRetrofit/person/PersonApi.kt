package com.tmdb.data.api.implRetrofit.person

import com.tmdb.api.model.person.Person
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {
    @GET("person/{person_id}")
    suspend fun personDetails(
        @Path("person_id") personId: Int,
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
        @Query("append_to_response") appendToResponse: String? = null
    ): ApiResponse<Person, NetworkErrorModel>
}
