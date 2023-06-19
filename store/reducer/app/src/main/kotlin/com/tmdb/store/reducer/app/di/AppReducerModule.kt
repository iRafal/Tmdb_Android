package com.tmdb.store.reducer.app.di

import com.tmdb.feature.movie.details.MovieDetailsFeatureSlice
import com.tmdb.feature.reducer.HomeFeatureSlice
import com.tmdb.store.reducer.app.AppReducer
import com.tmdb.store.reducer.app.createAppReducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
object AppReducerModule {

    @Provides
    fun appReducer(
        homeFeatureSlice: HomeFeatureSlice,
        movieDetailsFeatureSlice: MovieDetailsFeatureSlice
    ): AppReducer = createAppReducer(homeFeatureSlice, movieDetailsFeatureSlice)
}
