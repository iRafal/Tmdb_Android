package com.tmdb_test.ui.home.data.mapping

import com.tmdb_test.store.state.home.HomeFeatureState
import com.tmdb_test.ui.home.data.HomeMovieSection.NOW_PLAYING
import com.tmdb_test.ui.home.data.HomeMovieSection.NOW_POPULAR
import com.tmdb_test.ui.home.data.HomeMovieSection.TOP_RATED
import com.tmdb_test.ui.home.data.HomeMovieSection.UPCOMING
import com.tmdb_test.ui.home.data.HomeUiData

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