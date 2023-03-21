package com.tmdb.store.base.feature

interface FeatureSlice<G, Env, F> {
    val reducer: FeatureReducer<G, Env, F>
}