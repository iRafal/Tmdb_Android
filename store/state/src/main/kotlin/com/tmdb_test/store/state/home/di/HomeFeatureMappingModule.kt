package com.tmdb_test.store.state.home.di

import com.tmdb_test.store.state.home.MoviesDataToFeatureStateMapper
import com.tmdb_test.store.state.mapping.mapDataToFeatureStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureMappingModule {

    @Provides
    fun moviesDataToFeatureStateMapper(
    ): @JvmSuppressWildcards MoviesDataToFeatureStateMapper = ::mapDataToFeatureStateImpl
}