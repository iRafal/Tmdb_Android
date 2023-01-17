package com.tmdb_test.data.db.realm.movie.dao

import com.tmdb_test.data.db.realm.movie.MovieEntity
import io.realm.Realm
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieDaoImpl @Inject constructor(private val realm: Realm): MovieDao {
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

    override suspend fun nowPlayingMovies(): List<MovieEntity> = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                val entities = it.where(MovieEntity::class.java)
                    .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING, true)
                    .findAll()
                    .toList()
                continuation.resume(entities)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun nowPopularMovies(): List<MovieEntity> = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                val entities = it.where(MovieEntity::class.java)
                    .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR, true)
                    .findAll()
                    .toList()
                continuation.resume(entities)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun topRatedMovies(): List<MovieEntity> = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                val entities = it.where(MovieEntity::class.java)
                    .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED, true)
                    .findAll()
                    .toList()
                continuation.resume(entities)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
        }
    }

    override suspend fun upcomingMovies(): List<MovieEntity> = suspendCoroutine { continuation ->
        try {
            realm.executeTransaction {
                val entities = it.where(MovieEntity::class.java)
                    .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING, true)
                    .findAll()
                    .toList()
                continuation.resume(entities)
            }
        } catch (e: Throwable) {
            continuation.resumeWithException(e)
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