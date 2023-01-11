package com.tmdb_test.data.api.impl_ktor.discover

import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.api.impl_ktor.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class DiscoverApiImpl(private val client: HttpClient, private val baseUrl: String) : DiscoverApi {

    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get("${baseUrl}discover/movie") {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun discoverTv(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get("${baseUrl}discover/tv") {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }
}