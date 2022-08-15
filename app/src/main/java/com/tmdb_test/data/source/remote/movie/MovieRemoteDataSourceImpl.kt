package com.tmdb_test.data.source.remote.movie

import com.tmdb_test.data.api.util.NetworkErrorModel
import com.tmdb_test.data.api.impl_retrofit.movie.MovieApi
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiResponse

//import com.tmdb_test.data.api.impl.movie.MovieApi
//class MovieRemoteDataSourceImpl(private val api: MovieApi) : MovieRemoteDataSource {
//    override suspend fun movie(
//        movieId: Int,
//        language: String?,
//        appendToResponse: String?
//    ): ApiResponse<Movie> = api.movie(movieId, language, appendToResponse)
//
//    override suspend fun latestMovie(
//        language: String?
//    ): ApiResponse<Movie> = api.latestMovie(language)
//
//    override suspend fun nowPlayingMovies(
//        language: String?,
//        page: Int?,
//        region: String?
//    ): ApiResponse<DataPage<Movie>> = api.nowPlayingMovies(language, page, region)
//
//    override suspend fun nowPopularMovies(
//        language: String?,
//        page: Int?,
//        region: String?
//    ): ApiResponse<DataPage<Movie>> = api.nowPopularMovies(language, page, region)
//
//    override suspend fun topRatedMovies(
//        language: String?,
//        page: Int?,
//        region: String?
//    ): ApiResponse<DataPage<Movie>> = api.topRatedMovies(language, page, region)
//
//    override suspend fun upcomingMovies(
//        language: String?,
//        page: Int?,
//        region: String?
//    ): ApiResponse<DataPage<Movie>> = api.upcomingMovies(language, page, region)
//}

class MovieRemoteDataSourceImpl(private val api: MovieApi) : MovieRemoteDataSource {
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
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.nowPlayingMovies(language, page, region)

    override suspend fun nowPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.nowPopularMovies(language, page, region)

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