package com.tmdb_test.data.source.remote.impl.genre

import com.tmdb_test.api.impl_retrofit.genre.GenreApi
import com.tmdb_test.api.model.genre.GenresList
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.source.remote.contract.genre.GenreRemoteDataSource
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