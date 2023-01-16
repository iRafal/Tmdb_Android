package com.tmdb_test.data.source.remote.contract

import com.tmdb_test.data.model.movie.MovieDataModel

interface MovieLocalDataSource {

    suspend fun movie(movieId: Int): MovieDataModel?

    suspend fun nowPlayingMovies(
        page: Int? = null,
        pageSize: Int? = null,
    ): List<MovieDataModel>

    suspend fun nowPopularMovies(
        page: Int? = null,
        pageSize: Int? = null,
    ): List<MovieDataModel>

    suspend fun topRatedMovies(
        page: Int? = null,
        pageSize: Int? = null,
    ): List<MovieDataModel>

    suspend fun upcomingMovies(
        page: Int? = null,
        pageSize: Int? = null,
    ): List<MovieDataModel>

    suspend fun insert(movie: MovieDataModel)

    suspend fun insertAll(movies: List<MovieDataModel>)

    suspend fun insertByCategories(
        nowPlaying: List<MovieDataModel> = listOf(),
        nowPopular: List<MovieDataModel> = listOf(),
        topRatedMovies: List<MovieDataModel> = listOf(),
        upcomingMovies: List<MovieDataModel> = listOf()
    )
}