package com.tmdb_test.data.api.impl_ktor.discover

import com.tmdb_test.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.api.impl_ktor.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class DiscoverApiImpl @Inject constructor(
    private val client: HttpClient,
    private val urlProvider: DiscoverUrlProvider
) : DiscoverApi {
    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.discoverMovieUrl) {
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
        client.get(urlProvider.discoverTvUrl) {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }
}