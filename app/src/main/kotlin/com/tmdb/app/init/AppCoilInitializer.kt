package com.tmdb.app.init

import android.content.Context
import androidx.startup.Initializer
import coil3.SingletonImageLoader
import com.tmdb.MovieApp

class AppCoilInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        SingletonImageLoader.setSafe { (context as MovieApp).appComponent.imageLoader }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}