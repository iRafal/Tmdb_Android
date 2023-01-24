package com.tmdb_test.store.reducer.app.di

import com.tmdb_test.store.reducer.app.AppReducer
import com.tmdb_test.store.reducer.app.createAppReducer
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