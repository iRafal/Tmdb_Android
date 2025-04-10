package com.tmdb

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import androidx.work.Configuration
import coil3.ImageLoader
import coil3.SingletonImageLoader
import com.tmdb.di.component.app.AppComponent
import com.tmdb.di.component.app.AppComponentStore
import com.tmdb.di.component.store.AppStoreComponent
import com.tmdb.ui.core.di.module.ImageLoadingModule
import com.tmdb.ui.core.util.logging.android.AndroidLogging
import com.tmdb.util.di.qualifiers.ApplicationScope
import javax.inject.Inject

class MovieApp : Application() , Configuration.Provider {

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR)
            .setWorkerFactory(appComponent.workerFactory)
            .build()

    @Inject
    @ApplicationScope
    @ImageLoadingModule.CoilOkHttpImageLoader
    lateinit var coilImageLoader: ImageLoader

    private val appComponentStore: AppComponentStore by lazy { AppComponentStore() }

    val appComponent: AppComponent
        get() = appComponentStore.component

    val appStoreComponent: AppStoreComponent
        get() = appComponentStore.appStoreComponentStore.component

    override fun onCreate() {
        super.onCreate()
        AndroidLogging.init(this)
//        initIoStrictPolicy()
        initComponents()
        initCoil()
    }

    private fun initComponents() {
        appComponentStore.init(this)
        appComponentStore.component.inject(this)
    }

    //TODO
//    override fun getWorkManagerConfiguration(): Configuration =
//        Configuration.Builder()
//            .setWorkerFactory(workerFactory)
//            .build()

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

    override fun onTerminate() {
        super.onTerminate()
        cleanComponents()
    }

    private fun cleanComponents() {
        appComponentStore.clean()
    }
}
