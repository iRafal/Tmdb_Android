package com.tmdb.store.base.feature

import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect

typealias FeatureReducer<G, Env, F> = (globalState: G, action: Action) -> Pair<F, Effect<Env>?>