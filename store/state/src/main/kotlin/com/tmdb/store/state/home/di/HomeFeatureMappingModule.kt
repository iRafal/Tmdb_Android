package com.tmdb.store.state.home.di

import com.tmdb.store.state.home.MoviesDataToFeatureStateMapper
import com.tmdb.store.state.mapping.mapDataToFeatureStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
object HomeFeatureMappingModule {

    @Provides
    fun moviesDataToFeatureStateMapper(): @JvmSuppressWildcards MoviesDataToFeatureStateMapper = ::mapDataToFeatureStateImpl
}
