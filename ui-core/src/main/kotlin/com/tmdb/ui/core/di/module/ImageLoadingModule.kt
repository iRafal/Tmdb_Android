package com.tmdb.ui.core.di.module

import android.content.Context
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.util.DebugLogger
import com.tmdb.ui.core.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun imageLoadingModule() = module {
    factory<ImageLoader>(named("CoilOkHttpImageLoader")) {
        ImageLoader.Builder(get<Context>())
            .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
            .components { add(OkHttpNetworkFetcherFactory()) }
            .build()
    }
}
