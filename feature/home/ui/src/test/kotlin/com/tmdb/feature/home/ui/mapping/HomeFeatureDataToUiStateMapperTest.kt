package com.tmdb.feature.home.ui.mapping

import com.tmdb.data.model.state.DataState
import com.tmdb.feature.home.ui.R
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.data.model.MovieGroup
import com.tmdb.feature.home.ui.util.ModelUtil
import com.tmdb.store.state.HomeFeatureState
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeFeatureDataToUiStateMapperTest {
    private val movieDataToHomeModelMapper: MovieDataToHomeModelMapper = MovieDataToHomeModelMapperImpl()
    private val mapper: HomeFeatureStateToUiStateMapper =
        HomeFeatureStateToUiStateMapperImpl(movieDataToHomeModelMapper)

    @Test
    fun mapHomeFeatureLoadingStateToUiState() {
        val input = HomeFeatureState.INITIAL.copyAsAllLoading
        val actual = mapper.map(input)
        val expected = HomeUiData(
            movieGroups = listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = emptyList(),
                    isLoading = true,
                    error = null
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = emptyList(),
                    isLoading = true,
                    error = null
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = emptyList(),
                    isLoading = true,
                    error = null
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = emptyList(),
                    isLoading = true,
                    error = null
                )
            )
        )

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun mapHomeFeatureNetworkErrorStateToUiState() {
        val input = HomeFeatureState.INITIAL.copy(
            nowPlayingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError()),
            popularMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError()),
            topRatedMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError()),
            upcomingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.NetworkError())
        )
        val expected = HomeUiData(
            isFullReload = false,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.NetworkError
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.NetworkError
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.NetworkError
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.NetworkError
                )
            )
        )
        val actual = mapper.map(input)

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun mapHomeFeatureErrorStateToUiState() {
        val input = HomeFeatureState(
            nowPlayingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error()),
            popularMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error()),
            topRatedMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error()),
            upcomingMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(movies = DataState.Error())
        )
        val expected = HomeUiData(
            isFullReload = false,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                )
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
            popularMovies = HomeFeatureState.MoviesGroup.INITIAL.copy(
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
            isFullReload = false,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = listOf(ModelUtil.uiModelMovie.copy(id = 1)),
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = listOf(ModelUtil.uiModelMovie.copy(id = 2)),
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = listOf(ModelUtil.uiModelMovie),
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = listOf(ModelUtil.uiModelMovie.copy(id = 3)),
                    isLoading = false,
                    error = null
                )
            )
        )
        val actual = mapper.map(input)

        assertEquals(expected = expected, actual = actual)
    }
}
