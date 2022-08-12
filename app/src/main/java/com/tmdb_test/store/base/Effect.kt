package com.tmdb_test.store.base

import com.tmdb_test.store.base.feature.Feature
import com.tmdb_test.store.env.AppEnv

interface Effect<Env> {
    operator fun invoke(executor: Executor<Env>)

    interface Executor<Env> {
        fun execute(
            feature: Feature,
            effectBlock: suspend Scope<Env>.() -> Unit
        )

        interface Scope<Env> {
            val env: Env

            fun dispatch(action: Action)
        }
    }
}


object Effects {
    fun <Env> empty(): Effect<Env>? = null

    fun <E> create(
        feature: Feature,
        effect: suspend Effect.Executor.Scope<E>.() -> Unit
    ): Effect<E> {
        return object : Effect<E> {
            override fun invoke(executor: Effect.Executor<E>) {
                executor.execute(feature, effect)
            }
        }
    }

    fun <Env> merge(vararg effects: Effect<Env>?) = object : Effect<Env> {
        override fun invoke(executor: Effect.Executor<Env>) {
            effects.asSequence().filterNotNull().forEach { effect -> effect(executor) }
        }
    }

    fun effect(
        effectExecutorScope: suspend Effect.Executor.Scope<AppEnv>.() -> Action,
        feature: Feature,
    ): Effect<AppEnv> = create(feature) {
        dispatch(action = effectExecutorScope())
    }
}