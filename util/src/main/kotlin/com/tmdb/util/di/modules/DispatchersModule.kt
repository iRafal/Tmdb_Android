package com.tmdb.util.di.modules

import com.tmdb.util.di.qualifiers.ApplicationScope
import com.tmdb.util.di.qualifiers.DispatcherDefault
import com.tmdb.util.di.qualifiers.DispatcherIo
import com.tmdb.util.di.qualifiers.DispatcherMain
import com.tmdb.util.di.qualifiers.DispatcherUnconfined
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
object DispatchersModule {

    @[DispatcherIo Provides ApplicationScope]
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @[DispatcherMain Provides ApplicationScope]
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @[DispatcherDefault Provides ApplicationScope]
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @[DispatcherUnconfined Provides ApplicationScope]
    fun unconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}
