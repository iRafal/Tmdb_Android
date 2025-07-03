package com.tmdb.di.module

import com.tmdb.ui.core.di.module.imageLoadingModule
import org.koin.dsl.module

fun uiModule() = module {
    includes(imageLoadingModule())
}
