package com.tmdb.ui.core.di.component.app

import android.app.Instrumentation
import android.content.Context
import android.content.res.Resources
import androidx.test.espresso.internal.inject.InstrumentationContext
import com.tmdb.ui.core.di.module.TestAppModule
import dagger.Component

@Component(modules = [TestAppModule::class])
interface TestAppComponent {
    val instrumentation: Instrumentation

    @InstrumentationContext
    val instrumentationContext: Context

    val resources: Resources

//    fun inject(inject: //)

    @Component.Builder
    interface Builder {
        fun build(): TestAppComponent
    }
}
