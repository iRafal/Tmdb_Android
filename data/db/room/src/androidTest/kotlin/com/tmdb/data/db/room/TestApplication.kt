package com.tmdb.data.db.room

import android.app.Application
import com.tmdb.data.db.room.di.module.testAppModule
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(testAppModule())
        }
    }
}
