package com.tmdb_test.store.app

import com.tmdb_test.store.base.Action
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.feature.Feature
import com.tmdb_test.store.env.contract.AppEnv
import com.tmdb_test.store.reducer.app.AppReducer
import com.tmdb_test.store.state.app.AppState
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AppStoreImpl(
    initialState: AppState,
    override val env: AppEnv,
    private val reducer: AppReducer,
    private val effectContext: CoroutineContext
) : AppStore {

    private val scopes = mutableMapOf<Feature, CoroutineScope>()

    override val stateFlow = MutableStateFlow(initialState)

    override fun dispatch(action: Action) {
        val (newState, effect) = reducer(state, action)
        stateFlow.value = newState
        effect?.invoke(this)
    }

    override fun execute(
        feature: Feature,
        effectBlock: suspend Effect.Executor.Scope<AppEnv>.() -> Unit
    ) {
        getFeatureScope(feature).launch(effectContext) {
            try {
                effectBlock()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun getFeatureScope(feature: Feature): CoroutineScope {
        val scope = scopes.getOrDefault(feature, null)
            ?: throw IllegalStateException("Feature $feature scope not found")

        if (!scope.isActive) throw IllegalStateException("Feature $feature is not active")

        return scope
    }

    override fun setFeatureScope(feature: Feature, scope: CoroutineScope) {
        scopes[feature] = scope.also {
            it.launch { awaitCancellation() }.invokeOnCompletion { scopes.remove(feature) }
        }
    }
}