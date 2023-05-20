package com.tmdb.ui.core.coil

import android.util.Log
import coil.util.Logger
import com.tmdb.ui.core.BuildConfig
import logcat.LogPriority.DEBUG
import logcat.asLog
import logcat.logcat

fun createCoilLogger(): Logger = object : Logger {
    override var level: Int = if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR

    override fun log(
        tag: String,
        priority: Int,
        message: String?,
        throwable: Throwable?
    ) {
        message?.let { logcat(DEBUG) { "Coil_Logs: $it" } }
        throwable?.let { logcat(DEBUG) { "Coil_Logs: " + throwable.asLog() } }
    }
}
