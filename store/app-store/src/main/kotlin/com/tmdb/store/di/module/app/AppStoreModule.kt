package com.tmdb.store.di.module.app

import com.tmdb.store.AppReducer
import com.tmdb.store.AppStore
import com.tmdb.store.di.module.env.appEnvModule
import com.tmdb.store.di.module.reducer.appReducerModule
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.util.di.modules.DISPATCHER_IO
import com.tmdb.util.di.modules.dispatchersModule
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun appStoreModule() = module {
    includes(appEnvModule(), appReducerModule(), dispatchersModule())
    factory<AppStore> {
        AppStore.create(
            reducer = get<AppReducer>(),
            env = get<AppEnv>(),
            effectContext = get<CoroutineDispatcher>(named(DISPATCHER_IO))
        )
    }
}
