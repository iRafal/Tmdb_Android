package com.tmdb.data.source.local.impl.realm.di

import kotlinx.coroutines.test.StandardTestDispatcher

object UnitTestServiceLocator {

    val textDispatcher = StandardTestDispatcher()
}
