package com.tmdb.data.source.remote.contract.movie

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel

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
