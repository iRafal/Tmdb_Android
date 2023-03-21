package com.tmdb.store.base

import com.tmdb.store.base.Effect.Executor.Scope
import com.tmdb.store.base.feature.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface Store<S, Env> {
    val state: S
        get() = stateFlow.value

    val stateFlow: StateFlow<S>

    val env: Env

    fun dispatch(action: Action)

    fun execute(feature: Feature, effectBlock: suspend Scope<Env>.() -> Unit)

    fun setFeatureScope(feature: Feature, scope: CoroutineScope)
}
