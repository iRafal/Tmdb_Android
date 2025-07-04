package com.tmdb.ui.core

import android.app.Application
import com.tmdb.ui.core.di.module.testAppModule
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(testAppModule())
        }
    }
}
