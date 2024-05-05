package com.tmdb.feature.movie.details.ui

import android.app.Application
import com.tmdb.feature.movie.details.ui.di.TestAppComponentStore

open class DaggerTestApplication: Application() {
    override fun onTerminate() {
        super.onTerminate()
        TestAppComponentStore.clean()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        TestAppComponentStore.clean()
    }
}
