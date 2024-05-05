package com.tmdb.store.base

import com.tmdb.store.base.feature.Feature
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

interface Store<S, Env> : Effect.Executor<Env>, Effect.Scope<Env> {
    val state: S
        get() = stateFlow.value

    val stateFlow: StateFlow<S>

    override val env: Env

    override fun dispatch(action: Action)

    override fun execute(feature: Feature, effectBlock: suspend Effect.Scope<Env>.() -> Unit)

    fun setFeatureScope(feature: Feature, scope: CoroutineScope)
}

fun <S, E> createStore(
    initialState: S,
    reducer: Reducer<S, E>,
    env: E,
    effectContext: CoroutineContext
): Store<S, E> {
    return StoreImpl(initialState, reducer, env, effectContext)
}

private class StoreImpl<S, E>(
    initialState: S,
    private val reducer: Reducer<S, E>,
    override val env: E,
    private val effectContext: CoroutineContext
) : Store<S, E>, Effect.Executor<E>, Effect.Scope<E> {

    private val scopesByFeature = mutableMapOf<Feature, CoroutineScope>()

    override val stateFlow = MutableStateFlow(initialState)

    override fun dispatch(action: Action): Unit = synchronized(stateFlow) {
        val (newState, effect) = reducer.reduce(stateFlow.value, action)
        stateFlow.value = newState
        effect?.invoke(this)
    }

    override fun execute(
        feature: Feature,
        effectBlock: suspend Effect.Scope<E>.() -> Unit
    ) {
        val executionStackTrace = (Exception()).stackTrace
        getFeatureLifecycleScope(feature).launch(effectContext) {
            try {
                effectBlock()
            } catch (e: Exception) {
                e.stackTrace += executionStackTrace
                throw e
            }
        }
    }

    override fun setFeatureScope(feature: Feature, scope: CoroutineScope) {
        scopesByFeature[feature] = scope
        scope.launch { awaitCancellation() }.invokeOnCompletion {
            if (scopesByFeature[feature] == scope) {
                scopesByFeature.remove(feature)
            }
        }
    }

    private fun getFeatureLifecycleScope(feature: Feature): CoroutineScope {
        val featureScope = scopesByFeature.getOrDefault(feature, null)
            ?: throw IllegalStateException("Feature $feature scope not found")

        if (!featureScope.isActive) throw IllegalStateException("Feature $feature is not active")

        return featureScope
    }
}
