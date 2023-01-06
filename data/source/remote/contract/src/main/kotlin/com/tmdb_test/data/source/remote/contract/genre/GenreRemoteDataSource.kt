package com.tmdb_test.data.source.remote.contract.genre

import com.tmdb_test.api.model.genre.GenresList
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel

interface GenreRemoteDataSource {

    suspend fun genreMovieList(language: String? = null): ApiResponse<GenresList, NetworkErrorModel>

    suspend fun genreTvList(language: String? = null): ApiResponse<GenresList, NetworkErrorModel>
}