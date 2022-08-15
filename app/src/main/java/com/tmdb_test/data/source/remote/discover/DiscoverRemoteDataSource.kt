package com.tmdb_test.data.source.remote.discover

import com.tmdb_test.data.api.util.NetworkErrorModel
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiResponse

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