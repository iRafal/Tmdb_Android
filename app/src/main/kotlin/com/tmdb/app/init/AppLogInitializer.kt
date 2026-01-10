package com.tmdb.app.init

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.tmdb.ui.core.util.logging.android.AndroidLogging

class AppLogInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        AndroidLogging.init(context as Application)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}