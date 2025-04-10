package com.tmdb.ui.core.util.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.tmdb.util.logging.AppLogger
import javax.inject.Inject
import javax.inject.Provider

typealias WorkerCreatorProvider = @JvmSuppressWildcards Provider<WorkerCreator<ListenableWorker>>

class MultiWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>, WorkerCreatorProvider>,
    private val logger: AppLogger,
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val workerClass = Class.forName(workerClassName).asSubclass(ListenableWorker::class.java)
        val foundEntry = workerFactories.entries.find { it.key.isAssignableFrom(workerClass) }
        val factory = foundEntry?.value
        if (factory == null) {
            logger.logDebug { "unknown worker class name: $workerClassName" }
            return null
        }
        return factory.get().create(workerParameters)
    }
}
