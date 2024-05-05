package com.tmdb.store.di.module.reducer

import com.tmdb.feature.home.reducer.HomeFeatureEffects
import com.tmdb.feature.home.reducer.HomeFeatureSlice
import com.tmdb.util.di.modules.DispatchersModule
import com.tmdb.util.di.qualifiers.ApplicationScope
import com.tmdb.util.di.qualifiers.DispatcherIo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module(includes = [DispatchersModule::class])
object HomeFeatureReducerModule {

    @Provides
    fun homeFeatureSlice(
        homeFeatureEffects: HomeFeatureEffects
    ) = HomeFeatureSlice(homeFeatureEffects)

    @[Provides ApplicationScope]
    fun homeFeatureEffects(@DispatcherIo dispatcher: CoroutineDispatcher) = HomeFeatureEffects(dispatcher)
}
