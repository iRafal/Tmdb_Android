package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.util.ModelUtil
import com.tmdb.store.state.FeatureState
import com.tmdb.store.state.home.HomeFeatureState
import com.tmdb.ui.core.data.UiState
import com.tmdb.ui.core.data.mapping.mapFeatureToUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeFeatureToUiStateMapperTest {
    private val movieDataToHomeModelMapper: MovieDataToHomeModelMapper =
        movieDataToHomeModelMapperImpl()

    private val movieDataItemsToHomeModelMapper: MovieDataItemsToHomeModelMapper =
        movieDataItemsToHomeModelMapperImpl(movieDataToHomeModelMapper)

    private val homeFeatureStateToUiSectionStateMapper: HomeFeatureStateToUiSectionStateMapper =
        mapFeatureToUiState(movieDataItemsToHomeModelMapper)

    private val mapper: HomeFeatureToUiStateMapper =
        homeFeatureToUiStateMapperImpl(homeFeatureStateToUiSectionStateMapper)

    @Test
    fun mapHomeFeatureLoadingStateToUiState() {
        val input = HomeFeatureState(
            nowPlayingMoviesState = FeatureState.Loading(),
            nowPopularMoviesState = FeatureState.Loading(),
            topRatedMoviesState = FeatureState.Loading(),
            upcomingMoviesState = FeatureState.Loading()
        )
        val actual = mapper.invoke(input)
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Loading(),
                NOW_POPULAR to UiState.Loading(),
                TOP_RATED to UiState.Loading(),
                UPCOMING to UiState.Loading()
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapHomeFeatureNetworkErrorStateToUiState() {
        val input = HomeFeatureState(
            nowPlayingMoviesState = FeatureState.NetworkError(),
            nowPopularMoviesState = FeatureState.NetworkError(),
            topRatedMoviesState = FeatureState.NetworkError(),
            upcomingMoviesState = FeatureState.NetworkError()
        )
        val actual = mapper.invoke(input)
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.NetworkError(),
                NOW_POPULAR to UiState.NetworkError(),
                TOP_RATED to UiState.NetworkError(),
                UPCOMING to UiState.NetworkError()
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapHomeFeatureErrorStateToUiState() {
        val input = HomeFeatureState(
            nowPlayingMoviesState = FeatureState.Error(),
            nowPopularMoviesState = FeatureState.Error(),
            topRatedMoviesState = FeatureState.Error(),
            upcomingMoviesState = FeatureState.Error()
        )
        val actual = mapper.invoke(input)
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Error(),
                NOW_POPULAR to UiState.Error(),
                TOP_RATED to UiState.Error(),
                UPCOMING to UiState.Error()
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun mapHomeFeatureSuccessStateToUiState() {
        val input = HomeFeatureState(
            nowPlayingMoviesState = FeatureState.Success(
                listOf(ModelUtil.movieDataModel)
            ),
            nowPopularMoviesState = FeatureState.Success(
                listOf(ModelUtil.movieDataModel.copy(id = 1))
            ),
            topRatedMoviesState = FeatureState.Success(
                listOf(ModelUtil.movieDataModel.copy(id = 2))
            ),
            upcomingMoviesState = FeatureState.Success(
                listOf(ModelUtil.movieDataModel.copy(id = 3))
            )
        )
        val actual = mapper.invoke(input)
        val expected = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Success(listOf(ModelUtil.uiModelMovie)),
                NOW_POPULAR to UiState.Success(listOf(ModelUtil.uiModelMovie.copy(id = 1))),
                TOP_RATED to UiState.Success(listOf(ModelUtil.uiModelMovie.copy(id = 2))),
                UPCOMING to UiState.Success(listOf(ModelUtil.uiModelMovie.copy(id = 3)))
            )
        )
        assertEquals(expected, actual)
    }
}
