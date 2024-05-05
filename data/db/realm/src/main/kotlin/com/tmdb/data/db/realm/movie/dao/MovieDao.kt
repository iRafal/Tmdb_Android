package com.tmdb.data.db.realm.movie.dao

import com.tmdb.data.db.realm.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

@Suppress("TooManyFunctions")
interface MovieDao {
    suspend fun insert(movie: MovieEntity)

    suspend fun insert(movies: List<MovieEntity>)

    suspend fun getAll(): List<MovieEntity>

    suspend fun nowPlayingMovies(
        page: Int? = null,
        pageSize: Int? = null
    ): List<MovieEntity>

    suspend fun nowPopularMovies(
        page: Int? = null,
        pageSize: Int? = null
    ): List<MovieEntity>

    suspend fun topRatedMovies(
        page: Int? = null,
        pageSize: Int? = null
    ): List<MovieEntity>

    suspend fun upcomingMovies(
        page: Int? = null,
        pageSize: Int? = null
    ): List<MovieEntity>

    suspend fun getById(id: Int): MovieEntity?

    fun observeAll(): Flow<List<MovieEntity>>

    suspend fun delete(movie: MovieEntity)

    suspend fun delete()
}
