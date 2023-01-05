package com.tmdb_test.data.source.remote.discover

import com.tmdb_test.api.impl_retrofit.util.ApiResponse
import com.tmdb_test.api.impl_retrofit.util.NetworkErrorModel
import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie

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