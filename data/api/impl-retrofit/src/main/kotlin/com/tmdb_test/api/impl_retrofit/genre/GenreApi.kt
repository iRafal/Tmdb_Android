package com.tmdb_test.api.impl_retrofit.genre

import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel
import com.tmdb_test.api.model.genre.GenresList
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {

    @GET("genre/movie/list")
    suspend fun genreMovieList(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
    ): ApiResponse<GenresList, NetworkErrorModel>

    @GET("genre/tv/list")
    suspend fun genreTvList(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        @Query("language") language: String? = null,
    ): ApiResponse<GenresList, NetworkErrorModel>
}
