package com.tmdb.util.di.modules

import com.tmdb.util.di.qualifiers.DispatcherDefault
import com.tmdb.util.di.qualifiers.DispatcherIo
import com.tmdb.util.di.qualifiers.DispatcherMain
import com.tmdb.util.di.qualifiers.DispatcherUnconfined
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object DispatchersModule {

    @[DispatcherIo Provides]
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @[DispatcherMain Provides]
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @[DispatcherDefault Provides]
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @[DispatcherUnconfined Provides]
    fun unconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}
