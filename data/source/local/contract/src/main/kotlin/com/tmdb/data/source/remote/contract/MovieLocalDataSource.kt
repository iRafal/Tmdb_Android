package com.tmdb.data.source.remote.contract

import com.tmdb.data.model.movie.MovieDataModel
import kotlinx.coroutines.flow.Flow

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

    suspend fun delete(movie: MovieDataModel)

    fun observeAll(): Flow<List<MovieDataModel>>

    suspend fun getAll(): List<MovieDataModel>

    suspend fun getById(id: Int): MovieDataModel?
}