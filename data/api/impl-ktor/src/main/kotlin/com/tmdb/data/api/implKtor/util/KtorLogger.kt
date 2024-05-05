package com.tmdb.data.api.implKtor.util

import io.ktor.client.plugins.logging.Logger
import logcat.LogPriority
import logcat.logcat
import javax.inject.Inject

class KtorLogger @Inject constructor() : Logger {
    override fun log(message: String) {
        logcat(LogPriority.DEBUG) { "Ktor Api Log: $message" }
    }
}
