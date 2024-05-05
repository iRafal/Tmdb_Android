package com.tmdb.ui.core.di.module

import android.content.Context
import coil.Coil
import coil.ImageLoader
import coil.util.Logger
import com.tmdb.ui.core.coil.createCoilLogger
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
    annotation class OkHttpClientCoil

    @[OkHttpClientCoil Provides ApplicationScope]
    fun coilOkkHttpClient(@InterceptorCoil coilLoggingInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(coilLoggingInterceptor)
            .build()

    @[Provides ApplicationScope]
    fun coilLogger(): Logger = createCoilLogger()

    @[Provides ApplicationScope]
    fun coilImageLoader(
        @ApplicationContext context: Context,
        @OkHttpClientCoil coilOkkHttpClient: OkHttpClient,
        coilLogger: Logger
    ): ImageLoader = Coil
        .imageLoader(context)
        .newBuilder()
        .okHttpClient(coilOkkHttpClient)
        .logger(coilLogger)
        .build()
}
