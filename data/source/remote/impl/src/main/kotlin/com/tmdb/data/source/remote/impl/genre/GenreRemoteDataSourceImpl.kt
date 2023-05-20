package com.tmdb.data.source.remote.impl.genre

import com.tmdb.api.impl_retrofit.genre.GenreApi
import com.tmdb.api.model.genre.Genre
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val api: GenreApi
) : GenreRemoteDataSource {

    override suspend fun genreMovieList(language: String?): ApiResponse<List<Genre>, NetworkErrorModel> {
        return when (val response = api.genreMovieList(language)) {
            is ApiResponse.ApiError -> ApiResponse.ApiError(response.body, response.code)
            is ApiResponse.NetworkError -> ApiResponse.NetworkError(response.cause)
            is ApiResponse.Success -> ApiResponse.Success(response.data.genres)
            is ApiResponse.UnknownError -> ApiResponse.UnknownError(response.cause)
        }
    }

    override suspend fun genreTvList(language: String?): ApiResponse<List<Genre>, NetworkErrorModel> {
        return when (val response = api.genreTvList(language)) {
            is ApiResponse.ApiError -> ApiResponse.ApiError(response.body, response.code)
            is ApiResponse.NetworkError -> ApiResponse.NetworkError(response.cause)
            is ApiResponse.Success -> ApiResponse.Success(response.data.genres)
            is ApiResponse.UnknownError -> ApiResponse.UnknownError(response.cause)
        }
    }
}
