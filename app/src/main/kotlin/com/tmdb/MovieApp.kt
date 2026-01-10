package com.tmdb

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.tmdb.di.component.app.AppComponent
import com.tmdb.di.component.app.AppComponentStore
import com.tmdb.di.component.store.AppStoreComponent

class MovieApp : Application() , Configuration.Provider {
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR)
            .setWorkerFactory(appComponent.workerFactory)
            .build()

    private val appComponentStore: AppComponentStore by lazy { AppComponentStore() }

    val appComponent: AppComponent
        get() = appComponentStore.component

    val appStoreComponent: AppStoreComponent
        get() = appComponentStore.appStoreComponentStore.component

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    private fun initComponents() {
        appComponentStore.init(this)
        appComponentStore.component.inject(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        cleanComponents()
    }

    private fun cleanComponents() {
        appComponentStore.clean()
    }
}
