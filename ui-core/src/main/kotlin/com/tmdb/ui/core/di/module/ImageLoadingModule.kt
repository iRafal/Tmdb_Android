package com.tmdb.ui.core.di.module

import android.content.Context
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.util.DebugLogger
import com.tmdb.ui.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
object ImageLoadingModule {
    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class CoilOkHttpImageLoader

    @[Provides CoilOkHttpImageLoader]
    fun coilImageLoader(
        @ApplicationContext context: Context,
    ): ImageLoader = ImageLoader.Builder(context)
        .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
        .components {
            add(OkHttpNetworkFetcherFactory())
        }
        .build()

//    @[Qualifier Retention(AnnotationRetention.BINARY)]
//    annotation class CoilKtorImageLoader
//
//    @[Provides CoilKtorImageLoader ApplicationScope]
//    fun coilKtorImageLoader(
//        context: Context,
//    ): ImageLoader = ImageLoader.Builder(context)
//        .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
//        .components {
//            add(
//                KtorNetworkFetcherFactory {
//                    HttpClient(Android)
//                }
//            )
//        }
//        .build()
}
