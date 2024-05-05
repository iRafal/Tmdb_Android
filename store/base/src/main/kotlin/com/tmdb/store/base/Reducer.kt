package com.tmdb.store.base

fun interface Reducer<S, Env> {
    fun reduce(state: S, action: Action): ReducedResult<S, Env>
}

typealias ReducedResult<S, Env> = Pair<S, Effect<Env>?>
