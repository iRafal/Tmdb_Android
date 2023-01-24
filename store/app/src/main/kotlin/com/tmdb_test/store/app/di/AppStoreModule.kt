package com.tmdb_test.store.app.di

import com.tmdb_test.store.app.AppStore
import com.tmdb_test.store.app.AppStoreImpl
import com.tmdb_test.store.env.contract.AppEnv
import com.tmdb_test.store.reducer.app.AppReducer
import com.tmdb_test.store.state.app.AppState
import com.tmdb_test.store.state.app.di.StoreStateModule.InitialAppState
import com.tmdb_test.utill.di.modules.DispatchersModule.DispatcherIo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
object AppStoreModule {
    @Provides
    @Singleton
    fun appStore(
        appEnv: AppEnv,
        appReducer: @JvmSuppressWildcards AppReducer,
        @DispatcherIo dispatcher: CoroutineDispatcher,
        @InitialAppState appState: AppState
    ): AppStore = AppStoreImpl(
        appState,
        appEnv,
        appReducer,
        effectContext = dispatcher
    )
}