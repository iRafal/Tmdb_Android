package com.tmdb.store.reducer.app.di

import com.tmdb.feature.store.reducer.home.HomeFeatureSlice
import com.tmdb.store.reducer.app.AppReducer
import com.tmdb.store.reducer.app.createAppReducer
import com.tmdb.store.reducer.movie.details.MovieDetailsFeatureSlice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppReducerModule {
    @Provides
    fun appReducer(
        homeFeatureSlice: HomeFeatureSlice,
        movieDetailsFeatureSlice: MovieDetailsFeatureSlice
    ): AppReducer = createAppReducer(homeFeatureSlice, movieDetailsFeatureSlice)
}