package com.tmdb.feature.movie.details.di

import com.tmdb.feature.movie.details.MovieDetailsFeatureSlice
import com.tmdb.feature.movie.details.MovieDetailsFeatureSliceImpl
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
