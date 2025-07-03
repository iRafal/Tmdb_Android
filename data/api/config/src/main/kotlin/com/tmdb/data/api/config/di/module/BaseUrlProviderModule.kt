package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
object BaseUrlProviderModule {
    @Provides
    fun baseUrlProvider(impl: BaseUrlProviderImpl): BaseUrlProvider = impl
}

fun baseUrlProviderModule() = module {
    factory<BaseUrlProvider> { BaseUrlProviderImpl() }
}
