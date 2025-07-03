package com.tmdb.store.di.module.app

import com.tmdb.store.AppReducer
import com.tmdb.store.AppStore
import com.tmdb.store.di.module.env.AppEnvModule
import com.tmdb.store.di.module.env.appEnvModule
import com.tmdb.store.di.module.reducer.AppReducerModule
import com.tmdb.store.di.module.reducer.appReducerModule
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.util.di.modules.DISPATCHER_IO
import com.tmdb.util.di.modules.DispatchersModule
import com.tmdb.util.di.modules.dispatchersModule
import com.tmdb.util.di.qualifiers.DispatcherIo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [AppEnvModule::class, AppReducerModule::class, DispatchersModule::class])
object AppStoreModule {
    @Provides
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

fun appStoreModule() = module {
    appEnvModule()
    appReducerModule()
    dispatchersModule()
    factory<AppStore>() {
        AppStore.create(
            reducer = get<AppReducer>(),
            env = get<AppEnv>(),
            effectContext = get<CoroutineDispatcher>(named(DISPATCHER_IO))
        )
    }
}
