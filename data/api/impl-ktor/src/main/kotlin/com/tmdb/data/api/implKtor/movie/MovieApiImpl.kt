package com.tmdb.data.api.implKtor.movie

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.data.api.implKtor.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class MovieApiImpl @Inject constructor(
    private val client: HttpClient,
    private val urlProvider: MovieUrlProvider
) : MovieApi {
    override suspend fun movie(
        movieId: Int,
        language: String?,
        appendToResponse: String?
    ): ApiResponse<Movie, NetworkErrorModel> {
        return runApiCall {
            client.get(urlProvider.movieUrl(movieId)) {
                parameter("language", language)
                parameter("append_to_response", appendToResponse)
            }.body()
        }
    }

    override suspend fun latestMovie(
        language: String?
    ): ApiResponse<Movie, NetworkErrorModel> {
        return runApiCall {
            client.get(urlProvider.latestMovieUrl) {
                parameter("language", language)
            }.body()
        }
    }

    override suspend fun nowPlayingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.nowPlayingMoviesUrl) {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun nowPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.nowPopularMoviesUrl) {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun topRatedMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.topRatedMoviesUrl) {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun upcomingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.upcomingMoviesUrl) {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }
}
