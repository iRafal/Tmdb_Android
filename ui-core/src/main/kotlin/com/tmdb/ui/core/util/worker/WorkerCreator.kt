package com.tmdb.ui.core.util.worker

import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

fun interface WorkerCreator<out T: ListenableWorker> {
    fun create(params: WorkerParameters): T
}
