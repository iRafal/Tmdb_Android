package com.tmdb.feature.movie.details.ui

import android.app.Application
import com.tmdb.feature.movie.details.ui.di.module.testAppModule
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(testAppModule())
        }
    }
}
