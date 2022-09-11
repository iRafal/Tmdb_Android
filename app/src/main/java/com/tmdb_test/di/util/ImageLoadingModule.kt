package com.tmdb_test.di.util

import android.content.Context
import android.util.Log
import coil.Coil
import coil.ImageLoader
import coil.util.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import logcat.LogPriority.DEBUG
import logcat.asLog
import logcat.logcat
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

@Module
@InstallIn(SingletonComponent::class)
class ImageLoadingModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorCoil

    @Provides
    @InterceptorCoil
    fun coilLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply { level = BODY }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttpClientCoil

    @OkHttpClientCoil
    fun coilOkkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(coilLoggingInterceptor())
            .build()
    }

    @Provides
    fun coilLogger(): Logger {
        return object : Logger {
            override var level: Int = Log.DEBUG

            override fun log(
                tag: String,
                priority: Int,
                message: String?,
                throwable: Throwable?
            ) {
                message?.let { logcat(DEBUG) { "Coil_Logs: $it" } }
                throwable?.let { logcat(DEBUG) { "Coil_Logs: " + throwable.asLog() } }
            }
        }
    }

    @Provides
    fun coilImageLoader(
        @ApplicationContext context: Context,
        coilLogger: Logger
    ): ImageLoader {
        return Coil
            .imageLoader(context)
            .newBuilder()
            .okHttpClient(coilOkkHttpClient())
            .logger(coilLogger).build()
    }
}