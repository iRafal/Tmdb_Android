//package com.tmdb_test.data.api.impl.genre
//
//import com.tmdb_test.api.model.Genre
//import ApiResponse
//
//interface GenreApi {
//
//    suspend fun genreMovieList(
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//    ): ApiResponse<List<Genre>>
//
//    suspend fun genreTvList(
//        /**
//         * Pass a ISO 639-1 value to display translated data for the fields that support it.
//         * minLength: 2
//         * pattern: ([a-z]{2})-([A-Z]{2})
//         * default: en-US
//         */
//        language: String? = null,
//    ): ApiResponse<List<Genre>>
//}
