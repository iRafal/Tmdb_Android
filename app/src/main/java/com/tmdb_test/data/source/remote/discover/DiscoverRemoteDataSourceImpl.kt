package com.tmdb_test.data.source.remote.discover

import com.tmdb_test.data.api.impl_retrofit.NetworkErrorModel
import com.tmdb_test.data.api.impl_retrofit.discover.DiscoverApi
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiResponse

//import com.tmdb_test.data.api.impl.discover.DiscoverApi
//class DiscoverRemoteDataSourceImpl(private val api: DiscoverApi) : DiscoverRemoteDataSource {
//
//    override suspend fun discoverTv(
//        language: String?,
//        page: Int?,
//        region: String?
//    ): ApiResponse<DataPage<Movie>> = api.discoverTv(language, page, region)
//
//    override suspend fun discoverMovie(
//        language: String?,
//        page: Int?,
//        region: String?
//    ): ApiResponse<DataPage<Movie>> = api.discoverMovie(language, page, region)
//}

class DiscoverRemoteDataSourceImpl(private val api: DiscoverApi) : DiscoverRemoteDataSource {

    override suspend fun discoverTv(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.discoverTv(language, page, region)

    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> =api.discoverMovie(language, page, region)
}