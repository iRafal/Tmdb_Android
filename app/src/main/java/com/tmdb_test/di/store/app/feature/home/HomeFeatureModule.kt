package com.tmdb_test.di.store.app.feature.home

import com.tmdb_test.data.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.feature.home.store.HomeFeatureSliceImpl
import com.tmdb_test.feature.mapping.MoviesDataToFeatureStateMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class HomeFeatureModule {

    @Provides
    fun homeFeatureSlice(
        moviesApiToDataStateMapper: @JvmSuppressWildcards MoviesApiToDataStateMapper,
        moviesDataToFeatureStateMapper: @JvmSuppressWildcards MoviesDataToFeatureStateMapper,
    ): HomeFeatureSlice = HomeFeatureSliceImpl(
        moviesApiToDataStateMapper,
        moviesDataToFeatureStateMapper,
    )
}