package com.tmdb.data.db.realm

import android.app.Application
import com.tmdb.data.db.realm.di.module.testAppModule
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(testAppModule())
        }
    }
}
