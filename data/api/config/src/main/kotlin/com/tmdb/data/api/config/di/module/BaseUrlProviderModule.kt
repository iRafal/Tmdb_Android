package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
import org.koin.dsl.module

fun baseUrlProviderModule() = module {
    factory<BaseUrlProvider> { BaseUrlProviderImpl() }
}
