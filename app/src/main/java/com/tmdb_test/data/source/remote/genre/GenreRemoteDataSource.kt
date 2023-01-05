package com.tmdb_test.data.source.remote.genre

import com.tmdb_test.data.api.util.NetworkErrorModel
import com.tmdb_test.api.model.genre.GenresList
import com.tmdb_test.data.api.util.ApiResponse

interface GenreRemoteDataSource {

    suspend fun genreMovieList(language: String? = null): ApiResponse<GenresList, NetworkErrorModel>

    suspend fun genreTvList(language: String? = null): ApiResponse<GenresList, NetworkErrorModel>
}