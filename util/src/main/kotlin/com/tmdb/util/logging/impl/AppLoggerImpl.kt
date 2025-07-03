package com.tmdb.util.logging.impl

import com.tmdb.util.logging.AppLogger
import logcat.LogPriority
import logcat.asLog
import logcat.logcat

@Suppress("TooManyFunctions")
class AppLoggerImpl: AppLogger {
    override fun logVerbose(tag: String?, message: () -> String) {
        logcat(LogPriority.VERBOSE, tag, message)
    }

    override fun logVerbose(tag: String?, error: Throwable, message: (() -> String)?) {
        logcat(LogPriority.VERBOSE, tag, getFinalMessage(error, message))
    }

    override fun logDebug(tag: String?, message: () -> String) {
        logcat(LogPriority.DEBUG, tag, message)
    }

    override fun logDebug(tag: String?, error: Throwable, message: (() -> String)?) {
        logcat(LogPriority.DEBUG, tag, getFinalMessage(error, message))
    }

    override fun logInfo(tag: String?, message: () -> String) {
        logcat(LogPriority.INFO, tag, message)
    }

    override fun logInfo(tag: String?, error: Throwable, message: (() -> String)?) {
        logcat(LogPriority.INFO, tag, getFinalMessage(error, message))
    }

    override fun logWarning(tag: String?, message: () -> String) {
        logcat(LogPriority.WARN, tag, message)
    }

    override fun logWarning(tag: String?, error: Throwable, message: (() -> String)?) {
        logcat(LogPriority.WARN, tag, getFinalMessage(error, message))
    }

    override fun logError(tag: String?, message: () -> String) {
        logcat(LogPriority.ERROR, tag, message)
    }

    override fun logError(tag: String?, error: Throwable, message: (() -> String)?) {
        logcat(LogPriority.ERROR, tag, getFinalMessage(error, message))
    }

    private fun getFinalMessage(error: Throwable, message: (() -> String)?): () -> String {
        val throwableAsLog = error.asLog()
        return if (message == null) {
            { throwableAsLog }
        } else {
            { "${message()}: $throwableAsLog" }
        }
    }
}
