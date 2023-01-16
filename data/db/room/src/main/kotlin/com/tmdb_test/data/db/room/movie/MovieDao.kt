package com.tmdb_test.data.db.room.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE movie.id is :id")
    suspend fun getById(id: Int): MovieEntity?

    @Query("SELECT * FROM movie")
    fun observeAll(): Flow<List<MovieEntity>>

    @Delete
    suspend fun delete(movie: MovieEntity): Int

    @Query("DELETE FROM movie")
    suspend fun delete(): Int
}