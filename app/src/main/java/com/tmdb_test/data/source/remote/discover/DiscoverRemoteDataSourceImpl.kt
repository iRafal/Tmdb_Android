package com.tmdb_test.data.source.remote.discover

import com.tmdb_test.api.impl_retrofit.discover.DiscoverApi
import com.tmdb_test.api.impl_retrofit.util.ApiResponse
import com.tmdb_test.api.impl_retrofit.util.NetworkErrorModel
import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import javax.inject.Inject


class DiscoverRemoteDataSourceImpl @Inject constructor(
    private val api: DiscoverApi
) : DiscoverRemoteDataSource {

    override suspend fun discoverTv(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.discoverTv(language, page, region)

    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.discoverMovie(language, page, region)
}