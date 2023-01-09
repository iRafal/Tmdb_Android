package com.tmdb_test.di.ui.home.data.mapping

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.ui.home.data.mapping.HomeFeatureStateToUiSectionStateMapper
import com.tmdb_test.ui.home.data.mapping.HomeFeatureToUiStateMapper
import com.tmdb_test.ui.home.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb_test.ui.home.data.mapping.MovieDataItemsToHomeModelMapper
import com.tmdb_test.ui.home.data.mapping.MovieDataToHomeModelMapper
import com.tmdb_test.ui.home.data.mapping.homeFeatureToUiStateMapperImpl
import com.tmdb_test.ui.home.data.mapping.homeMovieSectionToActionMapperImpl
import com.tmdb_test.ui.home.data.mapping.movieDataItemsToHomeModelMapperImpl
import com.tmdb_test.ui.home.data.mapping.movieDataToHomeModelMapperImpl
import com.tmdb_test.ui.util.data.mapping.mapFeatureToUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HomeUiDataMappingModule {
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
    ): @JvmSuppressWildcards HomeFeatureStateToUiSectionStateMapper =
        mapFeatureToUiState(movieDataItemsToHomeModelMapper)

    @Provides
    fun movieDataToHomeModelMapper(
        imageUrlProvider: ImageUrlProvider
    ): @JvmSuppressWildcards MovieDataToHomeModelMapper =
        movieDataToHomeModelMapperImpl(imageUrlProvider)

    @Provides
    fun homeMovieSectionToActionMapper(
    ): @JvmSuppressWildcards HomeMovieSectionToActionMapper = ::homeMovieSectionToActionMapperImpl
}