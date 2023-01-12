package com.tmdb_test.utill.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherIo

    @DispatcherIo
    @Provides
    @Singleton
    @JvmStatic
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherMain

    @DispatcherMain
    @Provides
    @Singleton
    @JvmStatic
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherDefault

    @DispatcherDefault
    @Provides
    @Singleton
    @JvmStatic
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherUnconfined

    @DispatcherUnconfined
    @Provides
    @Singleton
    @JvmStatic
    fun unconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}