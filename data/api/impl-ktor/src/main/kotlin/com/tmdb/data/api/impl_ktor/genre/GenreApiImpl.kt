package com.tmdb.data.api.impl_ktor.genre

import com.tmdb.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.api.model.genre.Genre
import com.tmdb.api.model.genre.GenresList
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.impl_ktor.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class GenreApiImpl @Inject constructor(
    private val client: HttpClient,
    private val urlProvider: GenreUrlProvider
) : GenreApi {
    override suspend fun genreMovieList(
        language: String?,
    ): ApiResponse<List<Genre>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.genreMovieListUrl) {
            parameter("language", language)
        }.body<GenresList>().genres
    }

    override suspend fun genreTvList(
        language: String?,
    ): ApiResponse<List<Genre>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.genreTvListUrl) {
            parameter("language", language)
        }.body<GenresList>().genres
    }
}
