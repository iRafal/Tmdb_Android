package com.tmdb_test.data.source.remote.genre

import com.tmdb_test.data.api.impl_retrofit.genre.GenreApi
import com.tmdb_test.data.api.model.genre.GenresList
import com.tmdb_test.data.api.util.ApiResponse
import com.tmdb_test.data.api.util.NetworkErrorModel
import javax.inject.Inject


class GenreRemoteDataSourceImpl @Inject constructor(
    private val api: GenreApi
) : GenreRemoteDataSource {

    override suspend fun genreMovieList(language: String?): ApiResponse<GenresList, NetworkErrorModel> {
        return api.genreMovieList(language)
    }

    override suspend fun genreTvList(language: String?): ApiResponse<GenresList, NetworkErrorModel> {
        return api.genreTvList(language)
    }
}