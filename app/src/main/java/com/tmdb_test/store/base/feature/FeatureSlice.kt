package com.tmdb_test.store.base.feature

interface FeatureSlice<G, Env, F> {
    val reducer: FeatureReducer<G, Env, F>
}