package com.tmdb_test.data.api.impl_ktor.movie

import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.api.impl_ktor.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiImpl(private val client: HttpClient, private val baseUrl: String) : MovieApi {

    override suspend fun movie(
        movieId: Int,
        language: String?,
        appendToResponse: String?,
    ): ApiResponse<Movie, NetworkErrorModel> {
        return runApiCall {
            client.get("${baseUrl}movie/$movieId") {
                parameter("language", language)
                parameter("append_to_response", appendToResponse)
            }.body()
        }
    }

    override suspend fun latestMovie(
        language: String?,
    ): ApiResponse<Movie, NetworkErrorModel> {
        return runApiCall {
            client.get("${baseUrl}movie/latest") {
                parameter("language", language)
            }.body()
        }
    }

    override suspend fun nowPlayingMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get("${baseUrl}movie/now_playing") {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun nowPopularMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get("${baseUrl}movie/popular") {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun topRatedMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get("${baseUrl}movie/top_rated") {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun upcomingMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get("${baseUrl}movie/upcoming") {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }
}