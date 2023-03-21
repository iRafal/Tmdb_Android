package com.tmdb.store.base

import com.tmdb.store.base.Effect.Executor
import com.tmdb.store.base.Effect.Executor.Scope
import com.tmdb.store.base.feature.Feature

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
        effect: suspend Scope<E>.() -> Unit
    ): Effect<E> = object : Effect<E> {
        override fun invoke(executor: Executor<E>) {
            executor.execute(feature, effect)
        }
    }

    fun <Env> merge(vararg effects: Effect<Env>?) = object : Effect<Env> {
        override fun invoke(executor: Executor<Env>) {
            effects.asSequence().filterNotNull().forEach { effect -> effect(executor) }
        }
    }

    fun <Env> effect(
        effectExecutorScope: suspend Scope<Env>.() -> Action,
        feature: Feature,
    ): Effect<Env> = create(feature) {
        dispatch(action = effectExecutorScope())
    }
}