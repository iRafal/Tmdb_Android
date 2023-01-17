package com.tmdb_test.data.db.realm.movie.dao

import com.tmdb_test.data.db.realm.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieDao {
    suspend fun insert(movie: MovieEntity)

    suspend fun insert(movies: List<MovieEntity>)

    suspend fun getAll(): List<MovieEntity>

    suspend fun nowPlayingMovies(): List<MovieEntity>

    suspend fun nowPopularMovies(): List<MovieEntity>

    suspend fun topRatedMovies(): List<MovieEntity>

    suspend fun upcomingMovies(): List<MovieEntity>

    suspend fun getById(id: Int): MovieEntity?

    fun observeAll(): Flow<List<MovieEntity>>
    suspend fun delete(movie: MovieEntity)

    suspend fun delete()
}