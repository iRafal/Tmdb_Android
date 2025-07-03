package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import org.koin.dsl.module

fun imageUrlModule() = module {
    factory<ImageUrlProvider> { ImageUrlProviderImpl(get<BaseUrlProvider>().apiImageUrl) }
}
