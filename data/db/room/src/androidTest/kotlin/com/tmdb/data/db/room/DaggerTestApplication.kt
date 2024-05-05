package com.tmdb.data.db.room

import android.app.Application
import com.tmdb.data.db.room.di.component.app.TestAppComponentStore

open class DaggerTestApplication : Application() {
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
