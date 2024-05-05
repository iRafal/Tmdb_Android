package com.tmdb.feature.home.ui.di

import android.app.Instrumentation
import android.content.Context
import android.content.res.Resources
import androidx.test.espresso.internal.inject.InstrumentationContext
import com.tmdb.feature.home.ui.HomeScreenTest
import com.tmdb.feature.home.ui.di.module.TestAppModule
import dagger.Component

@Component(modules = [TestAppModule::class])
interface TestAppComponent {

    val instrumentation: Instrumentation

    @InstrumentationContext
    val instrumentationContext: Context

    val resources: Resources

    fun inject(inject: HomeScreenTest)

    @Component.Builder
    interface Builder {
        fun build(): TestAppComponent
    }
}
