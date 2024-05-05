package com.tmdb

import android.app.Application
import com.tmdb.di.TestAppComponentStore

open class DaggerTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        TestAppComponentStore.init()
    }

    override fun onTerminate() {
        super.onTerminate()
        TestAppComponentStore.clean()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        TestAppComponentStore.clean()
    }
}
