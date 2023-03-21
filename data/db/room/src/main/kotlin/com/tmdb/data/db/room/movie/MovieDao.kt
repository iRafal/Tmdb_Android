package com.tmdb.data.db.room.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

private const val movieTable = MovieEntity.MOVIE_TABLE_NAME
private const val columId = MovieEntity.MOVIE_TABLE_COLUMN_ID
private const val columnTitle = MovieEntity.MOVIE_TABLE_COLUMN_TITLE
private const val columnVoteAverage = MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE
private const val columnReleaseDate = MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE
private const val columnPosterUrl = MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL
private const val columnNowPlaying = MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING
private const val columnNowPopular = MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR
private const val columnTopRated = MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED
private const val columnUpcoming = MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieEntity>)

    @Query("SELECT * FROM $movieTable")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $columnNowPlaying like 1")
    suspend fun nowPlayingMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE ${MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING} like 1 LIMIT :limit  OFFSET :offset")
    suspend fun nowPlayingMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $columnNowPopular like 1")
    suspend fun nowPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $columnNowPopular like 1 LIMIT :limit OFFSET :offset")
    suspend fun nowPopularMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $columnTopRated like 1")
    suspend fun topRatedMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $columnTopRated like 1 LIMIT :limit OFFSET :offset")
    suspend fun topRatedMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $columnUpcoming like 1")
    suspend fun upcomingMovies(): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $columnUpcoming like 1 LIMIT :limit OFFSET :offset")
    suspend fun upcomingMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $movieTable WHERE $movieTable.${columId} is :id")
    suspend fun getById(id: Int): MovieEntity?

    @Query("SELECT * FROM $movieTable")
    fun observeAll(): Flow<List<MovieEntity>>

    @Delete
    suspend fun delete(movie: MovieEntity): Int

    @Query("DELETE FROM $movieTable")
    suspend fun delete(): Int
}