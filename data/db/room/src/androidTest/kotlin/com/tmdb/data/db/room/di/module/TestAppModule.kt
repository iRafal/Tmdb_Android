package com.tmdb.data.db.room.di.module

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.koin.dsl.module

fun testAppModule() = module {
    includes(dispatchersModule(), testDbModule())
    factory { InstrumentationRegistry.getInstrumentation() }
    factory { get<Instrumentation>().targetContext }
    factory { get<Context>().resources }
}
