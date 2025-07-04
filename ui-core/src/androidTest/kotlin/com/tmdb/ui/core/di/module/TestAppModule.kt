package com.tmdb.ui.core.di.module

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.tmdb.ui.core.compose.resources.ResourceManager
import com.tmdb.ui.core.compose.resources.ResourceManagerImpl
import org.koin.dsl.module

fun testAppModule() = module {
    factory { InstrumentationRegistry.getInstrumentation() }
    factory { get<Instrumentation>().targetContext }
    factory { get<Context>().resources }
    factory<ResourceManager> { ResourceManagerImpl(get()) }
}
