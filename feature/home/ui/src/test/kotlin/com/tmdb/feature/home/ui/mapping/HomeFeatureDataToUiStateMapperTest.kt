package com.tmdb.feature.home.ui.mapping

import com.tmdb.data.model.state.DataState
import com.tmdb.feature.home.ui.data.mapping.HomeDataStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataItemsToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataItemsToHomeModelMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.util.ModelUtil
import com.tmdb.store.state.HomeFeatureState
import com.tmdb.ui.core.data.UiState
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeFeatureDataToUiStateMapperTest {
    private val movieDataToHomeModelMapper: MovieDataToHomeModelMapper = MovieDataToHomeModelMapperImpl()
    private val movieDataItemsToHomeModelMapper: MovieDataItemsToHomeModelMapper =
        MovieDataItemsToHomeModelMapperImpl(movieDataToHomeModelMapper)
    private val homeDataStateToUiStateMapper = HomeDataStateToUiStateMapper(movieDataItemsToHomeModelMapper)
    private val mapper: HomeFeatureStateToUiStateMapper =
        HomeFeatureStateToUiStateMapperImpl(homeDataStateToUiStateMapper)

    @Test
    fun mapHomeFeatureLoadingStateToUiState() {
        val input = HomeFeatureState.INITIAL.copyAsAllLoading
        val actual = mapper.map(input)
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Loading(),
                NOW_POPULAR to UiState.Loading(),
                TOP_RATED to UiState.Loading(),
                UPCOMING to UiState.Loading()
            )
        )

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun mapHomeFeatureNetworkErrorStateToUiState() {
        val input = HomeFeatureState.INITIAL.copy(
            nowPlayingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError()),
            nowPopularMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError()),
            topRatedMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError()),
            upcomingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError())
        )
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.NetworkError(),
                NOW_POPULAR to UiState.NetworkError(),
                TOP_RATED to UiState.NetworkError(),
                UPCOMING to UiState.NetworkError()
            )
        )
        val actual = mapper.map(input)

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun mapHomeFeatureErrorStateToUiState() {
        val input = HomeFeatureState(
            nowPlayingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error()),
            nowPopularMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error()),
            topRatedMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error()),
            upcomingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error())
        )
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Error(),
                NOW_POPULAR to UiState.Error(),
                TOP_RATED to UiState.Error(),
                UPCOMING to UiState.Error()
            )
        )
        val actual = mapper.map(input)

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun mapHomeFeatureSuccessStateToUiState() {
        val input = HomeFeatureState(
            nowPlayingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(
                movies = DataState.Success(listOf(ModelUtil.movieDataModel))
            ),
            nowPopularMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(
                movies = DataState.Success(listOf(ModelUtil.movieDataModel.copy(id = 1)))
            ),
            topRatedMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(
                movies = DataState.Success(listOf(ModelUtil.movieDataModel.copy(id = 2)))
            ),
            upcomingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(
                movies = DataState.Success(listOf(ModelUtil.movieDataModel.copy(id = 3)))
            ),
        )
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Success(listOf(ModelUtil.uiModelMovie)),
                NOW_POPULAR to UiState.Success(listOf(ModelUtil.uiModelMovie.copy(id = 1))),
                TOP_RATED to UiState.Success(listOf(ModelUtil.uiModelMovie.copy(id = 2))),
                UPCOMING to UiState.Success(listOf(ModelUtil.uiModelMovie.copy(id = 3)))
            )
        )
        val actual = mapper.map(input)

        assertEquals(expected = expected, actual = actual)
    }
}
