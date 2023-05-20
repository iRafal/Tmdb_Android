package com.tmdb.feature.store.reducer.home.di

import com.tmdb.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb.feature.store.reducer.home.HomeFeatureEffects
import com.tmdb.feature.store.reducer.home.HomeFeatureSlice
import com.tmdb.feature.store.reducer.home.HomeFeatureSliceImpl
import com.tmdb.store.state.home.MoviesDataToFeatureStateMapper
import com.tmdb.utill.di.modules.DispatchersModule.DispatcherIo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureReducerModule {

    @Provides
    fun homeFeatureSlice(
        moviesApiToDataStateMapper: @JvmSuppressWildcards MoviesApiToDataStateMapper,
        moviesDataToFeatureStateMapper: @JvmSuppressWildcards MoviesDataToFeatureStateMapper,
        homeFeatureEffects: HomeFeatureEffects
    ): HomeFeatureSlice = HomeFeatureSliceImpl(
        moviesApiToDataStateMapper,
        moviesDataToFeatureStateMapper,
        homeFeatureEffects
    )

    @Provides
    @Singleton
    fun homeFeatureEffects(
        @DispatcherIo dispatcher: CoroutineDispatcher
    ) = HomeFeatureEffects(dispatcher)
}
