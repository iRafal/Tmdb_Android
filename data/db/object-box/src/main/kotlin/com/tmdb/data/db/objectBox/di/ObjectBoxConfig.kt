package com.tmdb.data.db.objectBox.di

import android.content.Context
import com.tmdb.data.db.objectBox.BuildConfig
import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.db.objectBox.movie.MyObjectBox
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.android.Admin
import io.objectbox.config.DebugFlags
import io.objectbox.exception.DbException
import io.objectbox.kotlin.boxFor
import io.objectbox.sync.Sync
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
            logcat(DEBUG) {
                "Using ObjectBox version:[${BoxStore.getVersion()}], " +
                        "versionNative:[${BoxStore.getVersionNative()}], " +
                        "sync:[${Sync.isAvailable()}]"
            }
            val isAdminStarted = Admin(store).start(context)
            logcat(DEBUG) {
                "ObjectBox Admin started: $isAdminStarted"
            }
        }
    }

    fun moviesBox(store: BoxStore): Box<MovieEntity> = store.boxFor()

    private const val DB_NAME = "Movie.db"
}
