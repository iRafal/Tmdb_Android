package com.tmdb_test.data.source.remote.impl_ktor.genre

import com.tmdb_test.api.model.genre.Genre
import com.tmdb_test.api.model.util.ApiResponse
import com.tmdb_test.api.model.util.NetworkErrorModel
import com.tmdb_test.data.api.impl_ktor.genre.GenreApi
import com.tmdb_test.data.source.remote.contract.genre.GenreRemoteDataSource
import javax.inject.Inject


class GenreRemoteDataSourceImpl @Inject constructor(
    private val api: GenreApi
) : GenreRemoteDataSource {
    override suspend fun genreMovieList(language: String?): ApiResponse<List<Genre>, NetworkErrorModel> =
        api.genreMovieList(language)

    override suspend fun genreTvList(language: String?): ApiResponse<List<Genre>, NetworkErrorModel> =
        api.genreTvList(language)
}