package com.tmdb.feature.home.ui.di.module

import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieSectionToActionMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataItemsToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataItemsToHomeModelMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import dagger.Binds
import dagger.Module


@Module
interface HomeUiDataMappingModule {
    @Binds
    fun homeFeatureToUiStateMapper(impl: HomeFeatureStateToUiStateMapperImpl): HomeFeatureStateToUiStateMapper

    @Binds
    fun movieDataItemsToHomeModelMapper(impl: MovieDataItemsToHomeModelMapperImpl): MovieDataItemsToHomeModelMapper

    @Binds
    fun movieDataToHomeModelMapper(impl: MovieDataToHomeModelMapperImpl): MovieDataToHomeModelMapper

    @Binds
    fun homeMovieSectionToActionMapper(impl: HomeMovieSectionToActionMapperImpl): HomeMovieSectionToActionMapper
}
