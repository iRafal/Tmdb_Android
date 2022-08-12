package com.tmdb_test.store.base

typealias Reducer<S, Env> = (state: S, action: Action) -> Pair<S, Effect<Env>?>