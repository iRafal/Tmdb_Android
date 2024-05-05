package com.tmdb.data.api.implKtor.genre

import com.tmdb.api.model.genre.GenresList
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel

interface GenreApi {

    suspend fun genreMovieList(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        language: String? = null
    ): ApiResponse<GenresList, NetworkErrorModel>

    suspend fun genreTvList(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        language: String? = null
    ): ApiResponse<GenresList, NetworkErrorModel>
}
