package com.tmdb_test.store.state.di.modules

import com.tmdb_test.store.state.AppState
import com.tmdb_test.store.state.details.MovieDetailsFeatureState
import com.tmdb_test.store.state.home.HomeFeatureState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreStateModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InitialAppState

    @Provides
    @Singleton
    @JvmStatic
    @InitialAppState
    fun initialAppState(
        @InitialHomeFeatureState homeState: HomeFeatureState,
        @InitialMovieDetailsFeatureState movieDetailsState: MovieDetailsFeatureState
    ): AppState = AppState.createInitialState(homeState, movieDetailsState)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InitialHomeFeatureState

    @Provides
    @Singleton
    @JvmStatic
    @InitialHomeFeatureState
    fun initialHomeFeatureState(): HomeFeatureState = HomeFeatureState.INITIAL

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InitialMovieDetailsFeatureState

    @Provides
    @Singleton
    @JvmStatic
    @InitialMovieDetailsFeatureState
    fun initialMovieDetailsFeatureState(): MovieDetailsFeatureState = MovieDetailsFeatureState.INITIAL
}