//package com.tmdb_test.data.api.impl.movie
//
//import com.tmdb_test.data.api.model.data.DataPage
//import com.tmdb_test.data.api.model.movie.Movie
//import com.tmdb_test.data.api.util.ApiResponse
//
//interface MovieApi {
//
//    suspend fun movie(
//        movieId: Int,
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//        appendToResponse: String? = null,
//    ): ApiResponse<Movie>
//
//    suspend fun latestMovie(
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//    ): ApiResponse<Movie>
//
//    suspend fun nowPlayingMovies(
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//        /**
//         *
//         * Specify which page to query.
//         * minimum: 1
//         * maximum: 1000
//         * default: 1
//         */
//        page: Int? = null,
//        /**
//         * Specify a ISO 3166-1 code to filter release dates.
//         * Must be uppercase.
//         * pattern: ^[A-Z]{2}$
//         */
//        region: String? = null,
//    ): ApiResponse<DataPage<Movie>>
//
//    suspend fun nowPopularMovies(
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//        /**
//         *
//         * Specify which page to query.
//         * minimum: 1
//         * maximum: 1000
//         * default: 1
//         */
//        page: Int? = null,
//        /**
//         * Specify a ISO 3166-1 code to filter release dates.
//         * Must be uppercase.
//         * pattern: ^[A-Z]{2}$
//         */
//        region: String? = null,
//    ): ApiResponse<DataPage<Movie>>
//
//    suspend fun topRatedMovies(
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//        /**
//         *
//         * Specify which page to query.
//         * minimum: 1
//         * maximum: 1000
//         * default: 1
//         */
//        page: Int? = null,
//        /**
//         * Specify a ISO 3166-1 code to filter release dates.
//         * Must be uppercase.
//         * pattern: ^[A-Z]{2}$
//         */
//        region: String? = null,
//    ): ApiResponse<DataPage<Movie>>
//
//    suspend fun upcomingMovies(
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//        /**
//         *
//         * Specify which page to query.
//         * minimum: 1
//         * maximum: 1000
//         * default: 1
//         */
//        page: Int? = null,
//        /**
//         * Specify a ISO 3166-1 code to filter release dates.
//         * Must be uppercase.
//         * pattern: ^[A-Z]{2}$
//         */
//        region: String? = null,
//    ): ApiResponse<DataPage<Movie>>
//}