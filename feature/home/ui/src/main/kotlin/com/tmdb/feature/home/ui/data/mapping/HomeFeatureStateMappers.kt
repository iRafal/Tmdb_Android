package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.store.state.HomeFeatureState
import com.tmdb.ui.core.data.UiState
import com.tmdb.ui.core.data.mapping.DataStateToUiStateMapper
import javax.inject.Inject


class HomeDataStateToUiStateMapper @Inject constructor(
    private val movieDataItemsToHomeModelMapper: MovieDataItemsToHomeModelMapper
) : DataStateToUiStateMapper<List<MovieDataModel>, List<HomeUiData.Movie>>() {
    fun map(input: DataState<List<MovieDataModel>>?): UiState<List<HomeUiData.Movie>> {
        return map(input, movieDataItemsToHomeModelMapper::map)
    }
}

interface HomeFeatureStateToUiStateMapper {
    fun map(featureState: HomeFeatureState): HomeUiData
}

class HomeFeatureStateToUiStateMapperImpl @Inject constructor(
    private val homeDataStateToUiStateMapper: HomeDataStateToUiStateMapper
) : HomeFeatureStateToUiStateMapper {
    override fun map(featureState: HomeFeatureState): HomeUiData {

        fun HomeFeatureState.MoviesGroup.mapToUiState(): UiState<List<HomeUiData.Movie>> {
            return if (this.isLoading) {
                UiState.Loading()
            } else {
                homeDataStateToUiStateMapper.map(this.movies)
            }
        }

        val nowPlayingMoviesState = featureState.nowPlayingMovies.mapToUiState()
        val nowPopularMoviesState = featureState.nowPopularMovies.mapToUiState()
        val topRatedMoviesState = featureState.topRatedMovies.mapToUiState()
        val upcomingMoviesState = featureState.upcomingMovies.mapToUiState()

        return HomeUiData(
            movieSections = mapOf(
                NOW_PLAYING to nowPlayingMoviesState,
                NOW_POPULAR to nowPopularMoviesState,
                TOP_RATED to topRatedMoviesState,
                UPCOMING to upcomingMoviesState
            )
        )
    }
}
