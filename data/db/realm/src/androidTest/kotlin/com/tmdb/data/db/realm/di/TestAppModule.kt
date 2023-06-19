package com.tmdb.data.db.realm.di

import android.app.Instrumentation
import android.content.Context
import android.content.res.Resources
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.platform.app.InstrumentationRegistry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
object TestAppModule {

    @Provides
    fun instrumentation(): Instrumentation = InstrumentationRegistry.getInstrumentation()

    @[Provides InstrumentationContext]
    fun instrumentationContext(instrumentation: Instrumentation): Context =
        instrumentation.targetContext

    @Provides
    fun resources(@InstrumentationContext context: Context): Resources = context.resources
}