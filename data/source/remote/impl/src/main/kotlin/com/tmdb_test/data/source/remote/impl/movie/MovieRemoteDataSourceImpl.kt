package com.tmdb_test.data.source.remote.impl.movie

import com.tmdb_test.api.impl_retrofit.movie.MovieApi
import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel
import com.tmdb_test.data.source.remote.contract.movie.MovieRemoteDataSource
import javax.inject.Inject


class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: MovieApi
) : MovieRemoteDataSource {
    override suspend fun movie(
        movieId: Int,
        language: String?,
        appendToResponse: String?
    ): ApiResponse<Movie, NetworkErrorModel> = api.movie(movieId, language, appendToResponse)

    override suspend fun latestMovie(
        language: String?
    ): ApiResponse<Movie, NetworkErrorModel> = api.latestMovie(language)

    override suspend fun nowPlayingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> =
        api.nowPlayingMovies(language, page, region)

    override suspend fun nowPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> =
        api.nowPopularMovies(language, page, region)

    override suspend fun topRatedMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.topRatedMovies(language, page, region)

    override suspend fun upcomingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.upcomingMovies(language, page, region)
}