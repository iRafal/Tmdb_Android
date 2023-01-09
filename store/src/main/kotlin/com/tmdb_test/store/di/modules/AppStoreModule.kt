package com.tmdb_test.store.di.modules

import com.tmdb_test.store.di.modules.state.StoreStateModule.InitialAppState
import com.tmdb_test.store.app.AppReducer
import com.tmdb_test.store.state.AppState
import com.tmdb_test.store.app.AppStore
import com.tmdb_test.store.app.AppStoreImpl
import com.tmdb_test.store.app.createAppReducer
import com.tmdb_test.utill.di.modules.DispatchersModule.DispatcherIo
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
class AppStoreModule {
    @Singleton
    @Provides
    fun appStore(
        appEnv: AppEnv,
        appReducer: @JvmSuppressWildcards AppReducer,
        @DispatcherIo dispatcher: CoroutineDispatcher,
        @InitialAppState appState: AppState
    ): AppStore = AppStoreImpl(appState, appEnv, appReducer, effectContext = dispatcher)


    @Provides
    fun appReducer(homeFeatureSlice: HomeFeatureSlice): AppReducer =
        createAppReducer(homeFeatureSlice)
}