package com.tmdb.data.db.object_box.di

import kotlinx.coroutines.test.StandardTestDispatcher

object UnitTestServiceLocator {

    val textDispatcher = StandardTestDispatcher()
}
