package com.tmdb_test.store.di.modules.state

import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.state.home.HomeFeatureState
import com.tmdb_test.store.state.movie.details.MovieDetailsFeatureState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StoreStateModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InitialAppState

    @Singleton
    @Provides
    @InitialAppState
    fun initialAppState(
        @InitialHomeFeatureState homeState: HomeFeatureState,
        @InitialMovieDetailsFeatureState movieDetailsState: MovieDetailsFeatureState
    ): AppState = AppState.createInitialState(homeState, movieDetailsState)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InitialHomeFeatureState

    @Singleton
    @Provides
    @InitialHomeFeatureState
    fun initialHomeFeatureState(): HomeFeatureState = HomeFeatureState.INITIAL

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InitialMovieDetailsFeatureState

    @Singleton
    @Provides
    @InitialMovieDetailsFeatureState
    fun initialMovieDetailsFeatureState(): MovieDetailsFeatureState = MovieDetailsFeatureState.INITIAL
}