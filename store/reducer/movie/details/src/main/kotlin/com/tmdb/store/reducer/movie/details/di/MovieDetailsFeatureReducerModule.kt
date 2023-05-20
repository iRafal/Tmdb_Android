package com.tmdb.store.reducer.movie.details.di

import com.tmdb.store.reducer.movie.details.MovieDetailsFeatureSlice
import com.tmdb.store.reducer.movie.details.MovieDetailsFeatureSliceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsFeatureReducerModule {

    @Provides
    fun movieDetailsFeatureSlice(): MovieDetailsFeatureSlice = MovieDetailsFeatureSliceImpl()
}