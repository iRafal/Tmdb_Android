package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.store.state.home.HomeFeatureState

typealias HomeFeatureToUiStateMapper = (featureState: HomeFeatureState) -> HomeUiData

fun homeFeatureToUiStateMapperImpl(
    homeFeatureStateToUiSectionStateMapper: HomeFeatureStateToUiSectionStateMapper
): HomeFeatureToUiStateMapper {

    return { featureState ->
        val nowPlayingMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.nowPlayingMoviesState)
        val nowPopularMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.nowPopularMoviesState)
        val topRatedMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.topRatedMoviesState)
        val upcomingMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.upcomingMoviesState)

        HomeUiData(
            movieSections = mapOf(
                NOW_PLAYING to nowPlayingMoviesState,
                NOW_POPULAR to nowPopularMoviesState,
                TOP_RATED to topRatedMoviesState,
                UPCOMING to upcomingMoviesState,
            )
        )
    }
}