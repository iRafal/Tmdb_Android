package com.tmdb.data.source.remote.implRetrofit.genre

import com.tmdb.data.api.implRetrofit.genre.GenreApi
import com.tmdb.data.model.GenreDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.implRetrofit.mapping.GenreListApiModelToDataStateModelMapper
import javax.inject.Inject

class GenreRemoteDataSourceImpl @Inject constructor(
    private val api: GenreApi,
    private val genreListApiModelToDataStateModelMapper: GenreListApiModelToDataStateModelMapper,
) : GenreRemoteDataSource {

    override suspend fun genreMovieList(language: String?): DataState<List<GenreDataModel>> {
        val response = api.genreMovieList(language)
        return genreListApiModelToDataStateModelMapper.map(response)
    }

    override suspend fun genreTvList(language: String?): DataState<List<GenreDataModel>> {
        val response = api.genreTvList(language)
        return genreListApiModelToDataStateModelMapper.map(response)
    }
}
