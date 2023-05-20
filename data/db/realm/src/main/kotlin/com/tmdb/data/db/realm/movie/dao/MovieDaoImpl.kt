package com.tmdb.data.db.realm.movie.dao

import com.tmdb.data.db.realm.movie.MovieEntity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.executeTransactionAwait
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieDaoImpl @Inject constructor(
    private val realmConfiguration: RealmConfiguration
) : MovieDao {

    private val realm: Realm
        get() = Realm.getInstance(realmConfiguration)

    override suspend fun insert(movie: MovieEntity) {
        realm.executeTransactionAwait {
            it.insertOrUpdate(movie)
        }
    }

    override suspend fun insert(movies: List<MovieEntity>) {
        realm.executeTransactionAwait {
            it.insertOrUpdate(movies)
        }
    }

    override suspend fun getAll(): List<MovieEntity> = suspendCoroutine { continuation ->
        try {
            realm.where(MovieEntity::class.java).findAll().toList().also {
                continuation.resume(it)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun nowPlayingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> =
        getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING)

    override suspend fun nowPopularMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> =
        getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR)

    override suspend fun topRatedMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> =
        getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED)

    override suspend fun upcomingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieEntity> =
        getMoviesByCategories(page, pageSize, MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING)

    private suspend fun getMoviesByCategories(
        page: Int?,
        pageSize: Int?,
        categoryColumnName: String
    ): List<MovieEntity> = suspendCoroutine { continuation ->
        try {
            val limitAndOffset = processPageData(page, pageSize)
            if (limitAndOffset == null) {
                realm.where(MovieEntity::class.java)
                    .equalTo(categoryColumnName, true)
                    .findAll()
                    .toList()
                    .also {
                        continuation.resume(it)
                    }
                return@suspendCoroutine
            }

            val baseQuery = realm.where(MovieEntity::class.java)
                .equalTo(categoryColumnName, true)
                .findAll()

            applyPageDataToQuery(
                dropFirstItemsCount = limitAndOffset.first,
                takeLastItemsCount = limitAndOffset.second,
                baseQuery
            ).also {
                continuation.resume(it)
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
        pageSize: Int?
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
            realm.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_ID, id)
                .findFirst()
                .also { continuation.resume(it) }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override fun observeAll(): Flow<List<MovieEntity>> {
        val entities = realm.where(MovieEntity::class.java).findAll().toList()
        return flowOf(entities)
    }

    override suspend fun delete(movie: MovieEntity) {
        realm.executeTransactionAwait {
            it.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_ID, movie.id)
                .findFirst()
                ?.deleteFromRealm()
        }
    }

    override suspend fun delete() {
        realm.executeTransactionAwait {
            it.delete(MovieEntity::class.java)
        }
    }
}
