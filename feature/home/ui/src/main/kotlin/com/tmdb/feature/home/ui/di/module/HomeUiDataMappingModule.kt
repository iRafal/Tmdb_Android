package com.tmdb.feature.home.ui.di.module

import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import org.koin.dsl.module

fun homeUiDataMappingModule() = module {
    factory<HomeFeatureStateToUiStateMapper> { HomeFeatureStateToUiStateMapperImpl(get()) }
    factory<MovieDataToHomeModelMapper> { MovieDataToHomeModelMapperImpl() }
    factory<HomeMovieGroupToActionMapper> { HomeMovieGroupToActionMapperImpl() }
}
