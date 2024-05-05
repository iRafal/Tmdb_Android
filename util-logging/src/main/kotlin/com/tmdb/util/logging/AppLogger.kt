package com.tmdb.util.logging

interface AppLogger {
    fun logVerbose(tag: String? = null, message: () -> String)
    fun logVerbose(tag: String? = null, error: Throwable, message: (() -> String)? = null)
    fun logDebug(tag: String? = null, message: () -> String)
    fun logDebug(tag: String? = null, error: Throwable, message: (() -> String)? = null)
    fun logInfo(tag: String? = null, message: () -> String)
    fun logInfo(tag: String? = null, error: Throwable, message: (() -> String)? = null)
    fun logWarning(tag: String? = null, message: () -> String)
    fun logWarning(tag: String? = null, error: Throwable, message: (() -> String)? = null)
    fun logError(tag: String? = null, message: () -> String)
    fun logError(tag: String? = null, error: Throwable, message: (() -> String)? = null)
}