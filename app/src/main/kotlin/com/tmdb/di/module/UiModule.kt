package com.tmdb.di.module

import com.tmdb.ui.core.di.module.ImageLoadingModule
import dagger.Module

@Module(includes = [ImageLoadingModule::class])
interface UiModule

