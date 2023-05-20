package com.tmdb.data.local.impl.objectBox.di

import kotlinx.coroutines.test.StandardTestDispatcher

object UnitTestServiceLocator {

    val textDispatcher = StandardTestDispatcher()
}
