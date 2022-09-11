package com.tmdb_test.feature.home.content.mapping

import com.tmdb_test.feature.home.content.HomeMovieSection.NOW_PLAYING
import com.tmdb_test.feature.home.content.HomeMovieSection.NOW_POPULAR
import com.tmdb_test.feature.home.content.HomeMovieSection.TOP_RATED
import com.tmdb_test.feature.home.content.HomeMovieSection.UPCOMING
import com.tmdb_test.feature.home.data.HomeUiData
import com.tmdb_test.feature.home.store.HomeFeatureState
import com.tmdb_test.feature.mapping.HomeFeatureStateToUiSectionStateMapper

typealias HomeFeatureToUiStateMapper = (featureState: HomeFeatureState) -> HomeUiData

fun homeFeatureToUiStateMapperImpl(
    homeFeatureStateToUiSectionStateMapper: HomeFeatureStateToUiSectionStateMapper
): HomeFeatureToUiStateMapper {

    return { featureState ->
        val nowPlayingMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.nowPlayingMoviesState)
        val nowPopularMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.nowPopularMoviesState)
        val topRatedMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.topRatedMoviesState)
        val upcomingMoviesState = homeFeatureStateToUiSectionStateMapper(featureState.topRatedMoviesState)

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