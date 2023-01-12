package com.tmdb_test.ui.di.modules.data.mapping

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
object HomeUiDataMappingModule {
    @Provides
    @JvmStatic
    fun homeFeatureToUiStateMapper(
        homeFeatureStateToUiSectionStateMapper: @JvmSuppressWildcards HomeFeatureStateToUiSectionStateMapper,
    ): @JvmSuppressWildcards HomeFeatureToUiStateMapper =
        homeFeatureToUiStateMapperImpl(homeFeatureStateToUiSectionStateMapper)

    @Provides
    @JvmStatic
    fun movieDataItemsToHomeModelMapper(
        movieDataToHomeModelMapper: @JvmSuppressWildcards MovieDataToHomeModelMapper
    ): @JvmSuppressWildcards MovieDataItemsToHomeModelMapper =
        movieDataItemsToHomeModelMapperImpl(movieDataToHomeModelMapper)

    @Provides
    @JvmStatic
    fun homeFeatureStateToUiSectionStateMapper(
        movieDataItemsToHomeModelMapper: @JvmSuppressWildcards MovieDataItemsToHomeModelMapper
    ): @JvmSuppressWildcards HomeFeatureStateToUiSectionStateMapper =
        mapFeatureToUiState(movieDataItemsToHomeModelMapper)

    @Provides
    @JvmStatic
    fun movieDataToHomeModelMapper(
    ): @JvmSuppressWildcards MovieDataToHomeModelMapper = movieDataToHomeModelMapperImpl()

    @Provides
    @JvmStatic
    fun homeMovieSectionToActionMapper(
    ): @JvmSuppressWildcards HomeMovieSectionToActionMapper = ::homeMovieSectionToActionMapperImpl
}