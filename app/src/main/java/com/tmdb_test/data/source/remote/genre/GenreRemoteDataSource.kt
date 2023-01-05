package com.tmdb_test.data.source.remote.genre

import com.tmdb_test.api.impl_retrofit.util.ApiResponse
import com.tmdb_test.api.impl_retrofit.util.NetworkErrorModel
import com.tmdb_test.api.model.genre.GenresList

interface GenreRemoteDataSource {

    suspend fun genreMovieList(language: String? = null): ApiResponse<GenresList, NetworkErrorModel>

    suspend fun genreTvList(language: String? = null): ApiResponse<GenresList, NetworkErrorModel>
}