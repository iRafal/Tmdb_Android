package com.tmdb.store.base.feature

import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect

/***
 * Feature reducer, takes global state and all app actions
 * returns feature state
 */
fun interface FeatureReducer<G, Env, F> {
    fun map(globalState: G, action: Action): Pair<F, Effect<Env>?>
}
