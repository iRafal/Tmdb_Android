package com.tmdb_test.store.state.di.modules

import com.tmdb_test.store.state.AppState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreStateModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InitialAppState

    @Provides
    @Singleton
    @InitialAppState
    fun initialAppState(): AppState = AppState.createInitialState()
}