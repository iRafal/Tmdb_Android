package com.tmdb.data.db.room.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

private const val MOVIE_TABLE = MovieEntity.MOVIE_TABLE_NAME
private const val COLUM_ID = MovieEntity.MOVIE_TABLE_COLUMN_ID
private const val COLUMN_TITLE = MovieEntity.MOVIE_TABLE_COLUMN_TITLE
private const val COLUMN_VOTE_AVERAGE = MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE
private const val COLUMN_RELEASE_DATE = MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE
private const val COLUMN_POSTER_URL = MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL
private const val COLUMN_NOW_PLAYING = MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING
private const val COLUMN_NOW_POPULAR = MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR
private const val COLUMN_TOP_RATED = MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED
private const val COLUMN_UPCOMING = MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING

@Suppress("TooManyFunctions")
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieEntity>)

    @Query("SELECT * FROM $MOVIE_TABLE")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $COLUMN_NOW_PLAYING like 1")
    suspend fun nowPlayingMovies(): List<MovieEntity>

    @Query(
        "SELECT * FROM $MOVIE_TABLE WHERE ${MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING} like 1 LIMIT :limit  OFFSET :offset"
    )
    suspend fun nowPlayingMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $COLUMN_NOW_POPULAR like 1")
    suspend fun nowPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $COLUMN_NOW_POPULAR like 1 LIMIT :limit OFFSET :offset")
    suspend fun nowPopularMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $COLUMN_TOP_RATED like 1")
    suspend fun topRatedMovies(): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $COLUMN_TOP_RATED like 1 LIMIT :limit OFFSET :offset")
    suspend fun topRatedMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $COLUMN_UPCOMING like 1")
    suspend fun upcomingMovies(): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $COLUMN_UPCOMING like 1 LIMIT :limit OFFSET :offset")
    suspend fun upcomingMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $MOVIE_TABLE.$COLUM_ID is :id")
    suspend fun getById(id: Int): MovieEntity?

    @Query("SELECT * FROM $MOVIE_TABLE")
    fun observeAll(): Flow<List<MovieEntity>>

    @Delete
    suspend fun delete(movie: MovieEntity): Int

    @Query("DELETE FROM $MOVIE_TABLE")
    suspend fun delete(): Int
}
