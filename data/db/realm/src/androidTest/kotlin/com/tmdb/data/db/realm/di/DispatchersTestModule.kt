package com.tmdb.data.db.realm.di

import com.tmdb.utill.di.modules.DispatchersModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher


@OptIn(ExperimentalCoroutinesApi::class)
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class]
)
object DispatchersTestModule {

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class DispatcherTestStandard

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class DispatcherTestUnconfined

    @[DispatcherTestUnconfined Provides]
    fun standardTestDispatcher(): TestDispatcher = StandardTestDispatcher()

    @[DispatcherTestUnconfined Provides]
    fun unconfinedTestDispatcher(): TestDispatcher = UnconfinedTestDispatcher()

    @[DispatchersModule.DispatcherIo Provides]
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.Default
}