package com.tmdb.data.source.local.implRoom.di

import kotlinx.coroutines.test.StandardTestDispatcher

object UnitTestServiceLocator {

    val textDispatcher = StandardTestDispatcher()
}
