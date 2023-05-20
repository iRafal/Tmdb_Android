package com.tmdb.data.source.remote.impl.discover

import com.tmdb.api.impl_retrofit.discover.DiscoverApi
import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
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
