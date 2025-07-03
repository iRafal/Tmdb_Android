package com.tmdb

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import coil3.ImageLoader
import coil3.SingletonImageLoader
import com.tmdb.di.module.appModule
import com.tmdb.ui.core.di.module.ImageLoadingModule
import com.tmdb.ui.core.util.logging.android.AndroidLogging
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import javax.inject.Inject

@HiltAndroidApp
class MovieApp : Application() , Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR)
            .setWorkerFactory(workerFactory)
            .build()

    @Inject
    @ImageLoadingModule.CoilOkHttpImageLoader
    lateinit var coilImageLoader: ImageLoader

    val coilImageLoader2 : ImageLoader by inject(named("CoilOkHttpImageLoader"))

    override fun onCreate() {
        super.onCreate()
        initDi()
        AndroidLogging.init(this)
//        initIoStrictPolicy()
        initCoil()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@MovieApp)
            appModule()
        }
    }

    private fun initCoil() {
        SingletonImageLoader.setSafe { coilImageLoader }
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
                .build()
        )
    }
}
