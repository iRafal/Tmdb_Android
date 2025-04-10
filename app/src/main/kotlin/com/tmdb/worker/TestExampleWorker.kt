package com.tmdb.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.tmdb.store.AppStore
import com.tmdb.ui.core.util.worker.WorkerCreator
import com.tmdb.util.di.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Provider

class TestExampleWorker(
    appContext: Context,
    params: WorkerParameters,
    private val store: AppStore
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return Result.success()
    }

    class Factory @Inject constructor(
        @ApplicationContext private val appContext: Provider<Context>,
        private val store: Provider<AppStore>,
    ) : WorkerCreator<TestExampleWorker> {
        override fun create(params: WorkerParameters): TestExampleWorker {
            return TestExampleWorker(
                appContext.get(),
                params,
                store.get()
            )
        }
    }
}
