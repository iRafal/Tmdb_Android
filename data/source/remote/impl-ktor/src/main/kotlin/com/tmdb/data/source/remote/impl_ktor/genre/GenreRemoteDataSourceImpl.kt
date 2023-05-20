package com.tmdb.data.source.remote.impl_ktor.genre

import com.tmdb.api.model.genre.Genre
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.impl_ktor.genre.GenreApi
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val api: GenreApi
) : GenreRemoteDataSource {
    override suspend fun genreMovieList(
        language: String?
    ): ApiResponse<List<Genre>, NetworkErrorModel> = api.genreMovieList(language)

    override suspend fun genreTvList(
        language: String?
    ): ApiResponse<List<Genre>, NetworkErrorModel> = api.genreTvList(language)
}
