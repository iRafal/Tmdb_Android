package com.tmdb.data.db.realm.di.module

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_MAIN = "DISPATCHER_MAIN"

const val DISPATCHER_TEST_STANDARD = "DISPATCHER_TEST_STANDARD"
const val DISPATCHER_TEST_UNCONFINED = "DISPATCHER_TEST_UNCONFINED"
const val DISPATCHER_TEST_DEFAULT = "DISPATCHER_TEST_DEFAULT"

@OptIn(ExperimentalCoroutinesApi::class)
fun dispatchersModule() = module {
    single<CoroutineDispatcher>(named(DISPATCHER_TEST_STANDARD)) { StandardTestDispatcher() }
    single<CoroutineDispatcher>(named(DISPATCHER_MAIN)) { Dispatchers.Main }

    single<CoroutineDispatcher>(named(DISPATCHER_TEST_UNCONFINED)) { UnconfinedTestDispatcher() }
    single<CoroutineDispatcher>(named(DISPATCHER_TEST_DEFAULT)) { Dispatchers.Default }
}

