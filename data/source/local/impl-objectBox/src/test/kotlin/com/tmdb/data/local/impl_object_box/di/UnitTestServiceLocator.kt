package com.tmdb.data.local.impl_object_box.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
object UnitTestServiceLocator {

    val textDispatcher = StandardTestDispatcher()
}