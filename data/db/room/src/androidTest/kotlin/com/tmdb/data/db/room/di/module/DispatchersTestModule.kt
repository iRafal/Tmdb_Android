package com.tmdb.data.db.room.di.module

import javax.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

//@OptIn(ExperimentalCoroutinesApi::class)
//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [DispatchersModule::class]
//)
//object DispatchersTestModule {
//
//    @[Qualifier Retention(AnnotationRetention.BINARY)]
//    annotation class DispatcherTestStandard
//
//    @[Qualifier Retention(AnnotationRetention.BINARY)]
//    annotation class DispatcherTestUnconfined
//
//    @[DispatcherTestStandard Provides]
//    fun standardTestDispatcher(): TestDispatcher = StandardTestDispatcher()
//
//    @[DispatcherTestUnconfined Provides]
//    fun unconfinedTestDispatcher(): TestDispatcher = UnconfinedTestDispatcher()
//
//    @[DispatcherDefault Provides]
//    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
//}
