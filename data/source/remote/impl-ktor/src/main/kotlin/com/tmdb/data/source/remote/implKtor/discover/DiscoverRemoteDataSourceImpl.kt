package com.tmdb.data.source.remote.implKtor.discover

import com.tmdb.data.api.implKtor.discover.DiscoverApi
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapper
import javax.inject.Inject

class DiscoverRemoteDataSourceImpl @Inject constructor(
    private val api: DiscoverApi,
    private val moviesListApiModelToDataStateModelMapper: MoviesListApiModelToDataStateModelMapper,
) : DiscoverRemoteDataSource {

    override suspend fun discoverTv(
        language: String?,
        page: Int?,
        region: String?
    ): DataState<List<MovieDataModel>> {
        return moviesListApiModelToDataStateModelMapper.map(api.discoverTv(language, page, region))
    }

    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?
    ): DataState<List<MovieDataModel>> {
        return moviesListApiModelToDataStateModelMapper.map(api.discoverMovie(language, page, region))
    }
}
