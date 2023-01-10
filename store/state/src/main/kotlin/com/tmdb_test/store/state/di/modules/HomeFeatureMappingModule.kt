package com.tmdb_test.store.state.di.modules

import com.tmdb_test.store.state.home.MoviesDataToFeatureStateMapper
import com.tmdb_test.store.state.mapping.mapDataToFeatureStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HomeFeatureMappingModule {

    @Provides
    fun moviesDataToFeatureStateMapper(
    ): @JvmSuppressWildcards MoviesDataToFeatureStateMapper = ::mapDataToFeatureStateImpl
}