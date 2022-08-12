package com.tmdb_test.store.base.feature

import com.tmdb_test.store.base.Action
import com.tmdb_test.store.base.Effect

typealias FeatureReducer<G, Env, F> = (globalState: G, action: Action) -> Pair<F, Effect<Env>?>