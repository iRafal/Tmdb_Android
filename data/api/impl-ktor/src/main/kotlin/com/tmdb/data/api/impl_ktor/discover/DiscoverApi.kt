package com.tmdb.data.api.impl_ktor.discover

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel


interface DiscoverApi {

    suspend fun discoverMovie(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        language: String? = null,
        /**
         *
         * Specify which page to query.
         * minimum: 1
         * maximum: 1000
         * default: 1
         */
        page: Int? = null,
        /**
         * Specify a ISO 3166-1 code to filter release dates.
         * Must be uppercase.
         * pattern: ^[A-Z]{2}$
         */
        region: String? = null,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    suspend fun discoverTv(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        language: String? = null,
        /**
         *
         * Specify which page to query.
         * minimum: 1
         * maximum: 1000
         * default: 1
         */
        page: Int? = null,
        /**
         * Specify a ISO 3166-1 code to filter release dates.
         * Must be uppercase.
         * pattern: ^[A-Z]{2}$
         */
        region: String? = null,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>
}