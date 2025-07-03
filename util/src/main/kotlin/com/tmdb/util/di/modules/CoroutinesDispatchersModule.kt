package com.tmdb.util.di.modules

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_MAIN = "DISPATCHER_MAIN"
const val DISPATCHER_DEFAULT = "DISPATCHER_DEFAULT"
const val DISPATCHER_UNCONFINED = "DISPATCHER_UNCONFINED"
const val DISPATCHER_IO = "DISPATCHER_IO"

fun dispatchersModule() = module {
    single<CoroutineDispatcher>(named(DISPATCHER_MAIN)) { Dispatchers.Main }
    single<CoroutineDispatcher>(named(DISPATCHER_DEFAULT)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(DISPATCHER_UNCONFINED)) { Dispatchers.Unconfined }
    single<CoroutineDispatcher>(named(DISPATCHER_IO)) { Dispatchers.IO }
}
