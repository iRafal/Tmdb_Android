package com.tmdb.feature.home.ui.di.module

import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import dagger.Binds
import dagger.Module

@Module
interface HomeUiDataMappingModule {
    @Binds
    fun homeFeatureToUiStateMapper(impl: HomeFeatureStateToUiStateMapperImpl): HomeFeatureStateToUiStateMapper

    @Binds
    fun movieDataToHomeModelMapper(impl: MovieDataToHomeModelMapperImpl): MovieDataToHomeModelMapper

    @Binds
    fun homeMovieGroupToActionMapper(impl: HomeMovieGroupToActionMapperImpl): HomeMovieGroupToActionMapper
}
