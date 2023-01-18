package com.tmdb_test.data.db.object_box.di

import android.content.Context
import com.tmdb_test.data.db.object_box.movie.MovieEntity
import com.tmdb_test.data.db.object_box.movie.MyObjectBox
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.DebugFlags
import io.objectbox.android.Admin
import io.objectbox.android.BuildConfig
import io.objectbox.exception.DbException
import io.objectbox.kotlin.boxFor
import logcat.LogPriority.DEBUG
import logcat.LogPriority.ERROR
import logcat.asLog
import logcat.logcat

object ObjectBoxConfig {
    fun store(context: Context): BoxStore = try {
        MyObjectBox.builder()
            .name(DB_NAME)
            .androidContext(context)
            .debugFlags(DebugFlags.LOG_QUERIES or DebugFlags.LOG_QUERY_PARAMETERS)
            .build()
            .also {
                initStoreAdmin(it, context)
            }
    } catch (e: DbException) {
        logcat(ERROR) { e.asLog() }
        throw e
    }

    /*
        Use [http://localhost:8090/index.html] to view a db values
     */
    private fun initStoreAdmin(store: BoxStore, context: Context) {
        if (BuildConfig.DEBUG) {
            logcat(DEBUG) { "Using ObjectBox ${BoxStore.getVersion()} (${BoxStore.getVersionNative()})" }
            Admin(store).start(context)
        }
    }

    fun moviesBox(store: BoxStore): Box<MovieEntity> = store.boxFor()

    private const val DB_NAME = "Movie.db"
}