package com.tmdb.store.di.module.reducer

import com.tmdb.feature.home.reducer.HomeFeatureReducer
import com.tmdb.feature.home.reducer.effects.HomeFeatureEffects
import com.tmdb.util.di.modules.DispatchersModule
import com.tmdb.util.di.qualifiers.DispatcherIo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module(includes = [DispatchersModule::class])
object HomeFeatureReducerModule {

    @Provides
    fun homeFeatureReducer(
        homeFeatureEffects: HomeFeatureEffects
    ) = HomeFeatureReducer(homeFeatureEffects)

    @Provides
    fun homeFeatureEffects(@DispatcherIo dispatcher: CoroutineDispatcher) = HomeFeatureEffects(dispatcher)
}
