package com.tmdb_test.store.app

import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Store
import com.tmdb_test.store.env.contract.AppEnv
import com.tmdb_test.store.state.app.AppState

interface AppStore : Store<AppState, AppEnv>, Effect.Executor<AppEnv>, Effect.Executor.Scope<AppEnv>