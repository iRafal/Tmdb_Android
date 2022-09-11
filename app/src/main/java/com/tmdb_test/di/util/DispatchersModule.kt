package com.tmdb_test.di.util

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
class DispatchersModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatcherIo

    @DispatcherIo
    @Provides
    @Singleton
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO
}