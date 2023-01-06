package com.tmdb_test.di.store.app.feature.home

import com.tmdb_test.api.config.url.image.ImageUrlProvider
import com.tmdb_test.feature.home.content.mapping.HomeFeatureToUiStateMapper
import com.tmdb_test.feature.home.content.mapping.MovieDataItemsToHomeModelMapper
import com.tmdb_test.feature.home.content.mapping.MovieDataToHomeModelMapper
import com.tmdb_test.feature.home.content.mapping.homeFeatureToUiStateMapperImpl
import com.tmdb_test.feature.home.content.mapping.movieDataItemsToHomeModelMapperImpl
import com.tmdb_test.feature.home.content.mapping.movieDataToHomeModelMapperImpl
import com.tmdb_test.feature.home.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb_test.feature.home.data.mapping.homeMovieSectionToActionMapperImpl
import com.tmdb_test.feature.mapping.HomeFeatureStateToUiSectionStateMapper
import com.tmdb_test.feature.mapping.MoviesDataToFeatureStateMapper
import com.tmdb_test.feature.mapping.mapDataToFeatureStateImpl
import com.tmdb_test.feature.mapping.mapFeatureToUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HomeFeatureMappingModule {

    @Provides
    fun moviesDataToFeatureStateMapper(
    ): @JvmSuppressWildcards MoviesDataToFeatureStateMapper = ::mapDataToFeatureStateImpl

    @Provides
    fun homeFeatureToUiStateMapper(
        homeFeatureStateToUiSectionStateMapper: @JvmSuppressWildcards HomeFeatureStateToUiSectionStateMapper,
    ): @JvmSuppressWildcards HomeFeatureToUiStateMapper =
        homeFeatureToUiStateMapperImpl(homeFeatureStateToUiSectionStateMapper)

    @Provides
    fun movieDataItemsToHomeModelMapper(
        movieDataToHomeModelMapper: @JvmSuppressWildcards MovieDataToHomeModelMapper
    ): @JvmSuppressWildcards MovieDataItemsToHomeModelMapper =
        movieDataItemsToHomeModelMapperImpl(movieDataToHomeModelMapper)

    @Provides
    fun homeFeatureStateToUiSectionStateMapper(
        movieDataItemsToHomeModelMapper: @JvmSuppressWildcards MovieDataItemsToHomeModelMapper
    ): @JvmSuppressWildcards HomeFeatureStateToUiSectionStateMapper = mapFeatureToUiState(movieDataItemsToHomeModelMapper)

    @Provides
    fun movieDataToHomeModelMapper(
        imageUrlProvider: com.tmdb_test.api.config.url.image.ImageUrlProvider
    ):  @JvmSuppressWildcards MovieDataToHomeModelMapper = movieDataToHomeModelMapperImpl(imageUrlProvider)

    @Provides
    fun homeMovieSectionToActionMapper(
    ):  @JvmSuppressWildcards HomeMovieSectionToActionMapper = ::homeMovieSectionToActionMapperImpl
}