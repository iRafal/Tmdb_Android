package com.tmdb.feature.movie.details.reducer.di

import com.tmdb.feature.movie.details.reducer.MovieDetailsFeatureSlice
import com.tmdb.feature.movie.details.reducer.MovieDetailsFeatureSliceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
object MovieDetailsFeatureReducerModule {

    @Provides
    fun movieDetailsFeatureSlice(): MovieDetailsFeatureSlice = MovieDetailsFeatureSliceImpl()
}
