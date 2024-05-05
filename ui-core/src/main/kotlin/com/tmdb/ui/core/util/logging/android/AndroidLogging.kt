package com.tmdb.ui.core.util.logging.android

import android.app.Application
import com.tmdb.ui.core.util.logging.android.AndroidReleaseLogcatLogger.Companion
import com.tmdb.ui.core.BuildConfig
import com.tmdb.util.logging.impl.AppLoggerImpl
import com.tmdb.util.logging.AppLogger
import logcat.AndroidLogcatLogger

val appLogger: AppLogger by lazy {
    AppLoggerImpl()
}

object AndroidLogging {
    fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            AndroidLogcatLogger.installOnDebuggableApp(application)
        } else {
            Companion.installOnReleaseApp(
                application,
                onErrorLog = { _, _, _ -> /* TODO pass logic */ }
            )
        }
    }
}

