package com.tmdb_test.data.db.room.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

private const val movieTable = MovieEntity.MOVIE_TABLE_NAME

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM $movieTable")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable")
    suspend fun nowPlayingMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable")
    suspend fun nowPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable")
    suspend fun topRatedMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable")
    suspend fun upcomingMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $movieTable.id is :id")
    suspend fun getById(id: Int): MovieEntity?

    @Query("SELECT * FROM $movieTable")
    fun observeAll(): Flow<List<MovieEntity>>

    @Delete
    suspend fun delete(movie: MovieEntity): Int

    @Query("DELETE FROM $movieTable")
    suspend fun delete(): Int
}