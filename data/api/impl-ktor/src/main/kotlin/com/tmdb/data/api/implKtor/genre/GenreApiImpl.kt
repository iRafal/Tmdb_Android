package com.tmdb.data.api.implKtor.genre

import com.tmdb.api.model.genre.GenresList
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.data.api.implKtor.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GenreApiImpl(
    private val client: HttpClient,
    private val urlProvider: GenreUrlProvider
) : GenreApi {
    override suspend fun genreMovieList(
        language: String?
    ): ApiResponse<GenresList, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.genreMovieListUrl) {
            parameter("language", language)
        }.body()
    }

    override suspend fun genreTvList(
        language: String?
    ): ApiResponse<GenresList, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.genreTvListUrl) {
            parameter("language", language)
        }.body()
    }
}
