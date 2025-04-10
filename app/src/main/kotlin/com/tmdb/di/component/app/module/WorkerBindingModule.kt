package com.tmdb.di.component.app.module

import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import com.tmdb.ui.core.di.qualifiers.WorkerKey
import com.tmdb.ui.core.util.worker.MultiWorkerFactory
import com.tmdb.ui.core.util.worker.WorkerCreator
import com.tmdb.util.di.modules.LoggingModule
import com.tmdb.worker.TestExampleWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [LoggingModule::class])
interface WorkerBindingModule {
    @Binds
    fun multiWorkerFactory(impl: MultiWorkerFactory): WorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(TestExampleWorker::class)
    fun testWorkerFactory(factory: TestExampleWorker.Factory): WorkerCreator<ListenableWorker>
}
