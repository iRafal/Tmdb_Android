package com.tmdb.app.init

import android.content.Context
import androidx.startup.Initializer
import com.tmdb.BuildConfig
import com.tmdb.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppKoinInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        startKoin {
            androidContext(context)
            modules(appModule())
            workManagerFactory()
            androidLogger(if (BuildConfig.DEBUG) Level.INFO else Level.NONE)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}