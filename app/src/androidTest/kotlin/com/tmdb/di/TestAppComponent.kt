package com.tmdb.di

import android.app.Instrumentation
import android.content.Context
import android.content.res.Resources
import androidx.test.espresso.internal.inject.InstrumentationContext
import dagger.Component

@Component(modules = [TestAppModule::class])
interface TestAppComponent {

    val instrumentation: Instrumentation

    @InstrumentationContext
    val instrumentationContext: Context

    val resources: Resources

    @Component.Builder
    interface Builder {
        fun build(): TestAppComponent
    }
}
