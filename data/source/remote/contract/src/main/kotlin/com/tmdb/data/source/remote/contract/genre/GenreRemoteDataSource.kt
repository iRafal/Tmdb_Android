package com.tmdb.data.source.remote.contract.genre

import com.tmdb.data.model.GenreDataModel
import com.tmdb.data.model.state.DataState

interface GenreRemoteDataSource {

    suspend fun genreMovieList(
        language: String? = null
    ): DataState<List<GenreDataModel>>

    suspend fun genreTvList(language: String? = null): DataState<List<GenreDataModel>>
}
