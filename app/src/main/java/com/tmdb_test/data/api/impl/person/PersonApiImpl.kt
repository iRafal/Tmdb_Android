//package com.tmdb_test.data.api.impl.person
//
//import com.tmdb_test.api.model.Person
//import com.tmdb_test.api.impl_retrofit.util.ApiResponse
//import com.tmdb_test.data.api.util.runApiCall
//import io.ktor.client.HttpClient
//import io.ktor.client.call.body
//import io.ktor.client.request.get
//import io.ktor.client.request.parameter
//
//
//class PersonApiImpl(private val client: HttpClient, private val baseUrl: String) : PersonApi {
//    override suspend fun personDetails(
//        personId: Int,
//        language: String?,
//        appendToResponse: String?,
//    ): ApiResponse<Person> {
//        return runApiCall {
//            client.get("${baseUrl}person/$personId}") {
//                parameter("language", language)
//                parameter("append_to_response", appendToResponse)
//            }.body()
//        }
//    }
//}