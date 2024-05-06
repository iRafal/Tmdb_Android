package com.tmdb.feature.home.reducer

import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.HomeFeatureState

internal fun HomeFeatureState.reduceHomeResetState(
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return HomeFeatureState.INITIAL to Effects.empty()
}
