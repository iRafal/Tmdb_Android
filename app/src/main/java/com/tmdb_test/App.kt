package com.tmdb_test

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import coil.Coil
import coil.ImageLoader
import com.tmdb_test.util.logging.AndroidReleaseLogcatLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import logcat.AndroidLogcatLogger

@HiltAndroidApp
class App: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var coilImageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()
        initLogging()
        initCoil()
        initIoStrictPolicy()
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            AndroidLogcatLogger.installOnDebuggableApp(this)
        } else {
            AndroidReleaseLogcatLogger.installOnReleaseApp(
                this,
                onErrorLog = { priority, tag, message -> /* TODO pass logs to crash tracking tool */ })
        }
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun initCoil() {
        Coil.setImageLoader(coilImageLoader)
    }

    private fun initIoStrictPolicy() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll()
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
//                .penaltyDeath()
                .build()
        )
    }
}