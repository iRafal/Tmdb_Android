package com.tmdb_test.store.app.di.modules

import com.tmdb_test.store.app.AppStore
import com.tmdb_test.store.app.AppStoreImpl
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.reducer.AppReducer
import com.tmdb_test.store.state.AppState
import com.tmdb_test.store.state.di.modules.StoreStateModule.InitialAppState
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