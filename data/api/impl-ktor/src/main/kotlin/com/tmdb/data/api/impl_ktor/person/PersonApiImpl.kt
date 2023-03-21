package com.tmdb.data.api.impl_ktor.person

import com.tmdb.api.config.url.provider.person.PersonUrlProvider
import com.tmdb.api.model.person.Person
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.impl_ktor.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject


class PersonApiImpl @Inject constructor(
    private val client: HttpClient,
    private val urlProvider: PersonUrlProvider,
) : PersonApi {
    override suspend fun personDetails(
        personId: Int,
        language: String?,
        appendToResponse: String?,
    ): ApiResponse<Person, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.personDetailsUrl(personId)) {
            parameter("language", language)
            parameter("append_to_response", appendToResponse)
        }.body()
    }
}