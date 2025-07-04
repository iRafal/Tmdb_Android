package com.tmdb.feature.home.ui.di.module

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.koin.dsl.module

fun testAppModule() = module {
    factory { InstrumentationRegistry.getInstrumentation() }
    factory { get<Instrumentation>().targetContext }
    factory { get<Context>().resources }
}
