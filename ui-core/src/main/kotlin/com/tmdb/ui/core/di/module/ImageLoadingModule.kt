package com.tmdb.ui.core.di.module

import android.content.Context
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.util.DebugLogger
import com.tmdb.ui.core.BuildConfig
import com.tmdb.util.di.qualifiers.ApplicationContext
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

@Module
object ImageLoadingModule {

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class InterceptorCoil

    @[Provides InterceptorCoil ApplicationScope]
    fun coilLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply { level = BODY }

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class CoilOkHttpClient

    @[CoilOkHttpClient Provides ApplicationScope]
    fun coilOkkHttpClient(@InterceptorCoil coilLoggingInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(coilLoggingInterceptor)
            .build()

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class CoilOkHttpImageLoader

    @[Provides CoilOkHttpImageLoader ApplicationScope]
    fun coilImageLoader(
        @ApplicationContext context: Context,
        @CoilOkHttpClient coilOkkHttpClient: OkHttpClient,
    ): ImageLoader = ImageLoader.Builder(context)
        .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
        .components {
            add(
                OkHttpNetworkFetcherFactory(
                    callFactory = { coilOkkHttpClient },
                )
            )
        }
        .build()

//    @[Qualifier Retention(AnnotationRetention.BINARY)]
//    annotation class CoilKtorImageLoader
//
//    @[Provides CoilKtorImageLoader ApplicationScope]
//    fun coilKtorImageLoader(
//        @ApplicationContext context: Context,
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
