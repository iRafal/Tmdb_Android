package com.tmdb.data.api.config.di.module

import org.koin.dsl.module

fun apiConfigModule() = module {
    includes(imageUrlModule(), urlProviderModule())
}
