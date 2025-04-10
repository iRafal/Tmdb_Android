package com.tmdb.di.component.app.module

import dagger.Module

@Module(includes = [UiModule::class, WorkerBindingModule::class])
internal object AppModule
