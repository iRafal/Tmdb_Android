package com.tmdb.ui.core.coil.di

import android.content.Context
import coil.Coil
import coil.ImageLoader
import coil.util.Logger
import com.tmdb.ui.core.coil.createCoilLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

@Module
@InstallIn(SingletonComponent::class)
object ImageLoadingModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterceptorCoil

    @Provides
    @InterceptorCoil
    @Singleton
    fun coilLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply { level = BODY }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttpClientCoil

    @OkHttpClientCoil
    @Provides
    @Singleton
    fun coilOkkHttpClient(@InterceptorCoil coilLoggingInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(coilLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun coilLogger(): Logger = createCoilLogger()

    @Provides
    @Singleton
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