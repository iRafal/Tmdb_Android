package com.tmdb_test.data.db.realm.movie

import io.realm.Realm
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MovieDao @Inject constructor(private val realm: Realm) {
    fun insert(movie: MovieEntity) {
        realm.executeTransaction {
            it.insertOrUpdate(movie)
        }
    }

    fun insert(movies: List<MovieEntity>) {
        realm.executeTransaction {
            movies.forEach { movie ->
                it.insertOrUpdate(movie)
            }
        }
    }

    fun getAll(): List<MovieEntity> {
        var entities: List<MovieEntity> = emptyList()
        realm.executeTransaction {
            entities = it.where(MovieEntity::class.java).findAll().toList()
        }
        return entities
    }

    fun nowPlayingMovies(): List<MovieEntity> {
        var entities: List<MovieEntity> = emptyList()
        realm.executeTransaction {
            entities = it.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING, true)
                .findAll()
                .toList()
        }
        return entities
    }

    fun nowPopularMovies(): List<MovieEntity> {
        var entities: List<MovieEntity> = emptyList()
        realm.executeTransaction {
            entities = it.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR, true)
                .findAll()
                .toList()
        }
        return entities
    }

    fun topRatedMovies(): List<MovieEntity> {
        var entities: List<MovieEntity> = emptyList()
        realm.executeTransaction {
            entities = it.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED, true)
                .findAll()
                .toList()
        }
        return entities
    }

    fun upcomingMovies(): List<MovieEntity> {
        var entities: List<MovieEntity> = emptyList()
        realm.executeTransaction {
            entities = it.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING, true)
                .findAll()
                .toList()
        }
        return entities
    }

    fun getById(id: Int): MovieEntity? {
        var entity: MovieEntity? = null
        realm.executeTransaction {
            entity = it.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING, true)
                .findFirst()
        }
        return entity
    }

    fun observeAll(): Flow<List<MovieEntity>> = callbackFlow {
        var entities: List<MovieEntity> = emptyList()
        realm.addChangeListener {
            realm.executeTransaction {
                entities = it.where(MovieEntity::class.java).findAll().toList()
            }
            trySend(entities)
        }
    }

    fun delete(movie: MovieEntity) {
        realm.executeTransaction {
            it.where(MovieEntity::class.java)
                .equalTo(MovieEntity.MOVIE_TABLE_COLUMN_ID, movie.id)
                .findFirst()
                ?.deleteFromRealm()
        }
    }

    fun delete() {
        realm.executeTransaction {
            it.delete(MovieEntity::class.java)
        }
    }
}