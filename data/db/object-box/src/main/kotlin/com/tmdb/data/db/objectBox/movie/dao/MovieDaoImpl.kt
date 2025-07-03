package com.tmdb.data.db.objectBox.movie.dao

import com.tmdb.data.db.objectBox.movie.MovieEntity
import io.objectbox.Box
import io.objectbox.kotlin.awaitCallInTx
import io.objectbox.kotlin.flow
import kotlinx.coroutines.flow.Flow

@Suppress("TooManyFunctions")
class MovieDaoImpl(private val box: Box<MovieEntity>) : MovieDao {
    override suspend fun insert(movie: MovieEntity) {
        box.store.awaitCallInTx { box.put(movie) }
    }

    override suspend fun insert(movies: List<MovieEntity>) {
        box.store.awaitCallInTx { box.put(movies) }
    }

    override suspend fun getAll(): List<MovieEntity> {
        return box.store.awaitCallInTx { box.all } ?: emptyList()
    }

    override suspend fun nowPlayingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING)

    override suspend fun nowPopularMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR)

    override suspend fun topRatedMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED)

    override suspend fun upcomingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING)

    private suspend fun getMoviesByCategories(
        page: Int?,
        pageSize: Int?,
        categoryColumnName: String
    ): List<MovieEntity> {
        val query = box.query().filter {
            when (categoryColumnName) {
                MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING -> it.isNowPlaying
                MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR -> it.isNowPopular
                MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED -> it.isTopRated
                MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING -> it.isUpcoming
                else -> throw IllegalArgumentException(
                    "Wrong category column name: $categoryColumnName"
                )
            }
        }.build()

        return box.store.awaitCallInTx {
            val limitAndOffset = processPageData(page, pageSize)
            if (limitAndOffset == null) {
                query.find()
            } else {
                query.find(limitAndOffset.first.toLong(), limitAndOffset.second.toLong())
            }
        } ?: emptyList()
    }

    private fun processPageData(
        page: Int?,
        pageSize: Int?
    ): Pair<Int, Int>? {
        val pageOrDefault = page ?: 0
        val pageSizeOrDefault = pageSize ?: 0
        if (pageOrDefault == 0 && pageSizeOrDefault == 0) return null

        return if (page == 0) {
            pageSizeOrDefault to 0
        } else {
            val currentPage = pageOrDefault - 1
            val dropFirstItemsCount = currentPage * pageSizeOrDefault
            val takeLastItemsCount = pageOrDefault * pageSizeOrDefault
            dropFirstItemsCount to takeLastItemsCount
        }
    }

    override suspend fun getById(id: Int): MovieEntity? {
        return box.store.awaitCallInTx {
            box.query().filter { it.movieId == id }.build().find().firstOrNull()
        }
    }

    override fun observeAll(): Flow<List<MovieEntity>> = box.query().build().flow()

    override suspend fun delete(movie: MovieEntity) {
        box.store.awaitCallInTx { box.remove(movie) }
    }

    override suspend fun delete() {
        box.store.awaitCallInTx { box.removeAll() }
    }
}
