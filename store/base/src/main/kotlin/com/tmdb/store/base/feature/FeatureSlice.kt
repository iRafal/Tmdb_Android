package com.tmdb.store.base.feature

/***
 * Feature Slice required for store creation
 */
interface FeatureSlice<G, Env, F> {
    val reducer: FeatureReducer<G, Env, F>
}
