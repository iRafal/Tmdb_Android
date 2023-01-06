package com.tmdb_test.data.source.remote.contract.discover

import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.util.serializer.ApiResponse
import com.tmdb_test.api.model.util.serializer.NetworkErrorModel

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