package com.tmdb.data.db.objectBox.di

import kotlinx.coroutines.test.StandardTestDispatcher

object UnitTestServiceLocator {
    val textDispatcher = StandardTestDispatcher()
}
