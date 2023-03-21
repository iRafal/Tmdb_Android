package com.tmdb.data.source.local.impl_realm.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
object UnitTestServiceLocator {

    val textDispatcher = StandardTestDispatcher()
}