package com.tmdb_test.di.store.app

import com.tmdb_test.di.util.DispatchersModule.DispatcherIo
import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.store.app.AppReducer
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.app.AppStore
import com.tmdb_test.store.app.AppStoreImpl
import com.tmdb_test.store.app.createAppReducer
import com.tmdb_test.store.env.AppEnv
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
        @DispatcherIo dispatcher: CoroutineDispatcher
    ): AppStore = AppStoreImpl(AppState.INITIAL, appEnv, appReducer, effectContext = dispatcher)


    @Provides
    fun appReducer(homeFeatureSlice: HomeFeatureSlice): AppReducer =
        createAppReducer(homeFeatureSlice)
}