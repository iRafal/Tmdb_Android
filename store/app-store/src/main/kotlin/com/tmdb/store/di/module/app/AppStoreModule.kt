package com.tmdb.store.di.module.app

import com.tmdb.store.AppReducer
import com.tmdb.store.AppStore
import com.tmdb.store.di.module.env.AppEnvModule
import com.tmdb.store.di.module.reducer.AppReducerModule
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.util.di.modules.DispatchersModule
import com.tmdb.util.di.qualifiers.ApplicationScope
import com.tmdb.util.di.qualifiers.DispatcherIo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module(includes = [AppEnvModule::class, AppReducerModule::class, DispatchersModule::class])
object AppStoreModule {
    @[Provides ApplicationScope]
    fun appStore(
        appEnv: AppEnv,
        appReducer: AppReducer,
        @DispatcherIo dispatcher: CoroutineDispatcher,
    ): AppStore = AppStore.create(
        appReducer,
        appEnv,
        effectContext = dispatcher
    )
}
