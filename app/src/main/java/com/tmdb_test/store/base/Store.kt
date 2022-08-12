package com.tmdb_test.store.base

import com.tmdb_test.store.base.feature.Feature
import com.tmdb_test.store.env.AppEnv
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface Store<S, Env> {
    val state: S
        get() = stateFlow.value

    val stateFlow: StateFlow<S>

    val env: Env

    fun dispatch(action: Action)

    fun execute(feature: Feature, effectBlock: suspend Effect.Executor.Scope<AppEnv>.() -> Unit)

    fun setFeatureScope(feature: Feature, scope: CoroutineScope)
}
