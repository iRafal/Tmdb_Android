package com.tmdb.store.di.module.reducer

import com.tmdb.feature.home.reducer.HomeFeatureReducer
import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.util.di.modules.DISPATCHER_IO
import com.tmdb.util.di.modules.dispatchersModule
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun homeFeatureReducerModule() = module {
    includes(dispatchersModule())
    factory<HomeFeatureReducer> { HomeFeatureReducer(get<HomeFeatureEffects>()) }
    factory<HomeFeatureEffects> { HomeFeatureEffects(get<CoroutineDispatcher>(named(DISPATCHER_IO))) }
}
