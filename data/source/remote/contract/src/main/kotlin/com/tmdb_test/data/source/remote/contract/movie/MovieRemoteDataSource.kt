package com.tmdb_test.data.source.remote.contract.movie

import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel

interface MovieRemoteDataSource {

    suspend fun movie(
        movieId: Int,
        language: String? = null,
        appendToResponse: String? = null
    ): ApiResponse<Movie, NetworkErrorModel>

    suspend fun latestMovie(language: String? = null): ApiResponse<Movie, NetworkErrorModel>

    suspend fun nowPlayingMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    suspend fun nowPopularMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    suspend fun topRatedMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    suspend fun upcomingMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>
}