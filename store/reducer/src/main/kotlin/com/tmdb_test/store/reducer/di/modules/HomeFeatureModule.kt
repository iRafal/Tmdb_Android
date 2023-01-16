package com.tmdb_test.store.reducer.di.modules

import com.tmdb_test.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.store.reducer.home.HomeFeatureEffects
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import com.tmdb_test.store.reducer.home.HomeFeatureSliceImpl
import com.tmdb_test.store.state.home.MoviesDataToFeatureStateMapper
import com.tmdb_test.utill.di.modules.DispatchersModule.DispatcherIo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureModule {

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