package com.tmdb.app.init

import android.content.Context
import androidx.startup.Initializer
import coil3.ImageLoader
import coil3.SingletonImageLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class AppCoilInitializer: Initializer<Unit>, KoinComponent {
    private val coilImageLoader : ImageLoader by inject(named("CoilOkHttpImageLoader"))

    override fun create(context: Context) {
        SingletonImageLoader.setSafe { coilImageLoader }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}