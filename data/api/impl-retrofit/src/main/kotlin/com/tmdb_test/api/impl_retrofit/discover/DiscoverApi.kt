package com.tmdb_test.api.impl_retrofit.discover

import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverApi {

    @GET("discover/movie")
    suspend fun discoverMovie(
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

    @GET("discover/tv")
    suspend fun discoverTv(
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