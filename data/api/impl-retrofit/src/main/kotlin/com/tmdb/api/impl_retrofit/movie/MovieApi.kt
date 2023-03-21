package com.tmdb.api.impl_retrofit.movie

import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{movie_id}")
    suspend fun movie(
        @Path("movie_id") movieId: Int,
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
        @Query("append_to_response") appendToResponse: String? = null,
    ): ApiResponse<Movie, NetworkErrorModel>

    @GET("movie/latest")
    suspend fun latestMovie(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
    ): ApiResponse<Movie, NetworkErrorModel>

    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
        /**
         *
         * Specify which page to query.
         * minimum: 1
         * maximum: 1000
         * default: 1
         */
        @Query("page") page: Int? = null,
        /**
         * Specify a ISO 3166-1 code to filter release dates.
         * Must be uppercase.
         * pattern: ^[A-Z]{2}$
         */
        @Query("region") region: String? = null,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    @GET("movie/popular")
    suspend fun nowPopularMovies(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
        /**
         *
         * Specify which page to query.
         * minimum: 1
         * maximum: 1000
         * default: 1
         */
        @Query("page") page: Int? = null,
        /**
         * Specify a ISO 3166-1 code to filter release dates.
         * Must be uppercase.
         * pattern: ^[A-Z]{2}$
         */
        @Query("region") region: String? = null,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    @GET("movie/top_rated")
    suspend fun topRatedMovies(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
        /**
         *
         * Specify which page to query.
         * minimum: 1
         * maximum: 1000
         * default: 1
         */
        @Query("page") page: Int? = null,
        /**
         * Specify a ISO 3166-1 code to filter release dates.
         * Must be uppercase.
         * pattern: ^[A-Z]{2}$
         */
        @Query("region") region: String? = null,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
        /**
         *
         * Specify which page to query.
         * minimum: 1
         * maximum: 1000
         * default: 1
         */
        @Query("page") page: Int? = null,
        /**
         * Specify a ISO 3166-1 code to filter release dates.
         * Must be uppercase.
         * pattern: ^[A-Z]{2}$
         */
        @Query("region") region: String? = null,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>
}