package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.model.state.DataState.Error
import com.tmdb.data.model.state.DataState.NetworkError
import com.tmdb.data.model.state.DataState.Success
import com.tmdb.feature.home.ui.R
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.UPCOMING
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.data.model.Movie
import com.tmdb.feature.home.ui.data.model.MovieGroup
import com.tmdb.store.state.HomeFeatureState
import javax.inject.Inject

interface HomeFeatureStateToUiStateMapper {
    fun map(featureState: HomeFeatureState): HomeUiData
}

class HomeFeatureStateToUiStateMapperImpl @Inject constructor(
    private val movieDataToHomeModelMapper: MovieDataToHomeModelMapper
) : HomeFeatureStateToUiStateMapper {
    override fun map(featureState: HomeFeatureState): HomeUiData {

        fun mapMovies(moviesState: DataState<List<MovieDataModel>>? = null): List<Movie> {
            return when (moviesState) {
                is Error, is NetworkError, null -> emptyList()
                is Success -> {
                    moviesState.data.map {
                        movieDataToHomeModelMapper.map(it)
                    }
                }
            }
        }

        val popularSection = MovieGroup(
            title = R.string.popular,
            type = POPULAR,
            movies = mapMovies(featureState.popularMovies.movies),
            isLoading = featureState.popularMovies.isLoading,
            error = when (featureState.popularMovies.movies) {
                is Error -> MovieGroup.Error.OtherError
                is NetworkError -> MovieGroup.Error.NetworkError
                is Success -> null
                null -> null
            }
        )

        val topRatedSection = MovieGroup(
            title = R.string.top_rated,
            type = TOP_RATED,
            movies = mapMovies(featureState.topRatedMovies.movies),
            isLoading = featureState.topRatedMovies.isLoading,
            error = when (featureState.topRatedMovies.movies) {
                is Error -> MovieGroup.Error.OtherError
                is NetworkError -> MovieGroup.Error.NetworkError
                is Success -> null
                null -> null
            }
        )

        val nowPlayingSection = MovieGroup(
            title = R.string.now_playing,
            type = NOW_PLAYING,
            movies = mapMovies(featureState.nowPlayingMovies.movies),
            isLoading = featureState.nowPlayingMovies.isLoading,
            error = when (featureState.nowPlayingMovies.movies) {
                is Error -> MovieGroup.Error.OtherError
                is NetworkError -> MovieGroup.Error.NetworkError
                is Success -> null
                null -> null
            }
        )

        val upcomingSection = MovieGroup(
            title = R.string.upcoming,
            type = UPCOMING,
            movies = mapMovies(featureState.upcomingMovies.movies),
            isLoading = featureState.upcomingMovies.isLoading,
            error = when (featureState.upcomingMovies.movies) {
                is Error -> MovieGroup.Error.OtherError
                is NetworkError -> MovieGroup.Error.NetworkError
                is Success -> null
                null -> null
            }
        )

        return HomeUiData(
            isFullReload = featureState.isFullReload,
            movieGroups = listOf(popularSection, topRatedSection, nowPlayingSection, upcomingSection)
        )
    }
}
