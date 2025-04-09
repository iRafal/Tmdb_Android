package com.tmdb.ui.core.coil

import coil3.util.Logger
import com.tmdb.ui.core.BuildConfig
import logcat.LogPriority.DEBUG
import logcat.asLog
import logcat.logcat

fun createCoilLogger(): Logger = object : Logger {

    override var minLevel: Logger.Level = if (BuildConfig.DEBUG) Logger.Level.Debug else Logger.Level.Error

    override fun log(tag: String, level: Logger.Level, message: String?, throwable: Throwable?) {
        message?.let { logcat(DEBUG) { "Coil_Logs: $it" } }
        throwable?.let { logcat(DEBUG) { "Coil_Logs: " + throwable.asLog() } }
    }
}
