package com.tmdb_test.store.reducer.di.modules

import com.tmdb_test.store.reducer.AppReducer
import com.tmdb_test.store.reducer.createAppReducer
import com.tmdb_test.store.reducer.home.HomeFeatureSlice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppReducerModule {
    @Provides
    fun appReducer(homeFeatureSlice: HomeFeatureSlice): AppReducer =
        createAppReducer(homeFeatureSlice)
}