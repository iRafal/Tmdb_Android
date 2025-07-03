package com.tmdb.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.tmdb.store.AppStore

class TestExampleWorker (
    appContext: Context,
    params: WorkerParameters,
    private val store: AppStore
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return Result.success()
    }
}
