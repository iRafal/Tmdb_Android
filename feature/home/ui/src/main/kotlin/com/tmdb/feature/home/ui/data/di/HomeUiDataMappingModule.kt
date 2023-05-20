package com.tmdb.feature.home.ui.data.di

import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiSectionStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataItemsToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.homeFeatureToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.homeMovieSectionToActionMapperImpl
import com.tmdb.feature.home.ui.data.mapping.movieDataItemsToHomeModelMapperImpl
import com.tmdb.feature.home.ui.data.mapping.movieDataToHomeModelMapperImpl
import com.tmdb.ui.core.data.mapping.mapFeatureToUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeUiDataMappingModule {
    @Provides
    fun homeFeatureToUiStateMapper(
        homeFeatureStateToUiSectionStateMapper: @JvmSuppressWildcards HomeFeatureStateToUiSectionStateMapper,
    ): @JvmSuppressWildcards HomeFeatureToUiStateMapper =
        homeFeatureToUiStateMapperImpl(homeFeatureStateToUiSectionStateMapper)

    @Provides
    fun homeFeatureStateToUiSectionStateMapper(
        movieDataItemsToHomeModelMapper: @JvmSuppressWildcards MovieDataItemsToHomeModelMapper
    ): @JvmSuppressWildcards HomeFeatureStateToUiSectionStateMapper =
        mapFeatureToUiState(movieDataItemsToHomeModelMapper)

    @Provides
    fun movieDataItemsToHomeModelMapper(
        movieDataToHomeModelMapper: @JvmSuppressWildcards MovieDataToHomeModelMapper
    ): @JvmSuppressWildcards MovieDataItemsToHomeModelMapper =
        movieDataItemsToHomeModelMapperImpl(movieDataToHomeModelMapper)

    @Provides
    fun movieDataToHomeModelMapper(
    ): @JvmSuppressWildcards MovieDataToHomeModelMapper = movieDataToHomeModelMapperImpl()

    @Provides
    fun homeMovieSectionToActionMapper(
    ): @JvmSuppressWildcards HomeMovieSectionToActionMapper = ::homeMovieSectionToActionMapperImpl
}