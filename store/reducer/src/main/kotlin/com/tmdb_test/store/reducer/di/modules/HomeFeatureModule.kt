package com.tmdb_test.store.reducer.di.modules

import com.tmdb_test.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import com.tmdb_test.store.reducer.home.HomeFeatureSliceImpl
import com.tmdb_test.store.state.home.MoviesDataToFeatureStateMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureModule {

    @Provides
    @JvmStatic
    fun homeFeatureSlice(
        moviesApiToDataStateMapper: @JvmSuppressWildcards MoviesApiToDataStateMapper,
        moviesDataToFeatureStateMapper: @JvmSuppressWildcards MoviesDataToFeatureStateMapper,
    ): HomeFeatureSlice = HomeFeatureSliceImpl(
        moviesApiToDataStateMapper,
        moviesDataToFeatureStateMapper,
    )
}