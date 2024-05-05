package com.tmdb.data.source.remote.contract.discover

import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState

interface DiscoverRemoteDataSource {
    suspend fun discoverMovie(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): DataState<List<MovieDataModel>>

    suspend fun discoverTv(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): DataState<List<MovieDataModel>>
}
