package com.tmdb.store.app.di

import com.tmdb.store.app.AppStore
import com.tmdb.store.app.AppStoreImpl
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.reducer.app.AppReducer
import com.tmdb.store.state.app.AppState
import com.tmdb.store.state.app.di.StoreStateModule.InitialAppState
import com.tmdb.utill.di.modules.DispatchersModule.DispatcherIo
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
