package com.tmdb.data.source.remote.contract.movie

import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState

interface MovieRemoteDataSource {

    suspend fun movie(
        movieId: Int,
        language: String? = null,
        appendToResponse: String? = null
    ): DataState<MovieDataModel>

    suspend fun latestMovie(language: String? = null): DataState<MovieDataModel>

    suspend fun nowPlayingMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): DataState<List<MovieDataModel>>

    suspend fun nowPopularMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): DataState<List<MovieDataModel>>

    suspend fun topRatedMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): DataState<List<MovieDataModel>>

    suspend fun upcomingMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): DataState<List<MovieDataModel>>
}
