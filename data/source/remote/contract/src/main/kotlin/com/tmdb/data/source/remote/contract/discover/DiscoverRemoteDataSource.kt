package com.tmdb.data.source.remote.contract.discover

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel

interface DiscoverRemoteDataSource {
    suspend fun discoverMovie(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>

    suspend fun discoverTv(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel>
}