package com.tmdb.di.module

import com.tmdb.store.di.module.app.AppStoreModule
import com.tmdb.store.di.module.app.appStoreModule
import com.tmdb.ui.core.di.module.imageLoadingModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        UiModule::class, AppStoreModule::class
    ]
)
internal object AppModule

fun appModule() = module {
    uiModule()
    appStoreModule()
}
