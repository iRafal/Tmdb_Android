package com.tmdb.data.local.impl_object_box.di

import kotlinx.coroutines.test.StandardTestDispatcher

object UnitTestServiceLocator {

    val textDispatcher = StandardTestDispatcher()
}
