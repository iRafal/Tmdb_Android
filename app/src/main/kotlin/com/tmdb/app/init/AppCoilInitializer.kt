package com.tmdb.app.init

import android.content.Context
import androidx.startup.Initializer
import coil3.SingletonImageLoader
import com.tmdb.di.AppInitializerEntryPoint
import dagger.hilt.android.EntryPointAccessors

class AppCoilInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            AppInitializerEntryPoint::class.java
        )
        SingletonImageLoader.setSafe { entryPoint.coilImageLoader() }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}