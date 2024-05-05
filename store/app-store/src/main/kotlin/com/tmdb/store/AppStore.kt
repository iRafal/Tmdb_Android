package com.tmdb.store

import com.tmdb.store.base.Reducer
import com.tmdb.store.base.Store
import com.tmdb.store.base.createStore
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.AppState
import kotlin.coroutines.CoroutineContext

interface AppStore : Store<AppState, AppEnv> {
    companion object {
        fun create(
            reducer: Reducer<AppState, AppEnv>,
            env: AppEnv,
            effectContext: CoroutineContext
        ): AppStore {
            return object : AppStore, Store<AppState, AppEnv> by createStore(
                initialState = AppState.INITIAL,
                reducer = reducer,
                env = env,
                effectContext = effectContext
            ) {}
        }
    }
}
