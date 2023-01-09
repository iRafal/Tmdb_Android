package com.tmdb_test.store.di.modules.util

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

//TODO move to proper module
@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherIo

    @DispatcherIo
    @Provides
    @Singleton
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherMain

    @DispatcherMain
    @Provides
    @Singleton
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherDefault

    @DispatcherDefault
    @Provides
    @Singleton
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherUnconfined

    @DispatcherUnconfined
    @Provides
    @Singleton
    fun unconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}