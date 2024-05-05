package com.tmdb.data.db.realm

import android.app.Application
import com.tmdb.data.db.realm.di.component.app.TestAppComponentStore

open class DaggerTestApplication : Application() {
    override fun onTerminate() {
        super.onTerminate()
        TestAppComponentStore.clean()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        TestAppComponentStore.clean()
    }
}
