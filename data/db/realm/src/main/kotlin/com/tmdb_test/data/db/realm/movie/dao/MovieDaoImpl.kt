package com.tmdb_test.data.db.realm.movie.dao

import com.tmdb_test.data.db.realm.movie.MovieEntity
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieDaoImpl @Inject constructor(private val realm: Realm) : MovieDao {
    override suspend fun insert(movie: MovieEntity) = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                it.insertOrUpdate(movie)
            }
            continuation.resume(Unit)
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun insert(movies: List<MovieEntity>) = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                movies.forEach { movie ->
                    it.insertOrUpdate(movie)
                }
            }
            continuation.resume(Unit)
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun getAll(): List<MovieEntity> = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                val entities = it.where(MovieEntity::class.java).findAll().toList()
                continuation.resume(entities)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun nowPlayingMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING)

    override suspend fun nowPopularMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR)

    override suspend fun topRatedMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED)

    override suspend fun upcomingMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieEntity> = getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING)

    private suspend fun getMoviesByCategories(
        page: Int?,
        pageSize: Int?,
        categoryColumnName: String
    ): List<MovieEntity> = suspendCoroutine { continuation ->
        val limitAndOffset = processPageData(page, pageSize)
        try {
            if (limitAndOffset == null) {
                var entities: List<MovieEntity> = emptyList()
                realm.executeTransaction {
                    entities = it.where(MovieEntity::class.java)
                        .equalTo(categoryColumnName, true)
                        .findAll()
                        .toList()
                }
                continuation.resume(entities)
                return@suspendCoroutine
            }

            realm.executeTransaction {
                val baseQuery = it.where(MovieEntity::class.java)
                    .equalTo(categoryColumnName, true)
                    .findAll()

                val entities = applyPageDataToQuery(
                    dropFirstItemsCount = limitAndOffset.first,
                    takeLastItemsCount = limitAndOffset.second,
                    baseQuery
                )
                continuation.resume(entities)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    private fun applyPageDataToQuery(
        dropFirstItemsCount: Int,
        takeLastItemsCount: Int,
        baseQueryResult: RealmResults<MovieEntity>
    ): List<MovieEntity> {
        return when {
            dropFirstItemsCount == 0 -> {
                baseQueryResult.take(takeLastItemsCount).toList()
            }
            takeLastItemsCount == 0 -> {
                baseQueryResult.take(dropFirstItemsCount).toList()
            }
            else -> {
                baseQueryResult.asSequence()
                    .take(dropFirstItemsCount + takeLastItemsCount)
                    .take(takeLastItemsCount)
                    .toList()
            }
        }
    }

    private fun processPageData(
        page: Int?,
        pageSize: Int?,
    ): Pair<Int, Int>? {
        val _page = page ?: 0
        val _pageSize = pageSize ?: 0
        if (_page == 0 && _pageSize == 0) return null

        return if (page == 0) {
            _pageSize to 0
        } else {
            val currentPage = _page - 1
            val dropFirstItemsCount = currentPage * _pageSize
            val takeLastItemsCount = _page * _pageSize
            dropFirstItemsCount to takeLastItemsCount
        }
    }

    override suspend fun getById(id: Int): MovieEntity? = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                val entity = it.where(MovieEntity::class.java)
                    .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_ID, id)
                    .findFirst()
                continuation.resume(entity)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override fun observeAll(): Flow<List<MovieEntity>> {
        val entities = realm.where(MovieEntity::class.java).findAll().toList()
        return flowOf(entities)
    }

    override suspend fun delete(movie: MovieEntity) = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                it.where(MovieEntity::class.java)
                    .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_ID, movie.id)
                    .findFirst()
                    ?.deleteFromRealm()
                continuation.resume(Unit)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun delete() = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                it.delete(MovieEntity::class.java)
                continuation.resume(Unit)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }
}