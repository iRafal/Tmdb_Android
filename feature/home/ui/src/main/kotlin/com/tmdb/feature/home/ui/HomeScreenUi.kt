@file:OptIn(ExperimentalMaterialApi::class)

package com.tmdb.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.feature.home.ui.components.MovieSection
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.data.model.Movie
import com.tmdb.feature.home.ui.data.model.MovieGroup
import com.tmdb.ui.core.compose.theme.TmdbTheme
import kotlinx.datetime.LocalDate

@Preview(showBackground = false, showSystemUi = false)
@Composable
private fun HomeAllSectionsLoadingPreview() {
    TmdbTheme {
        HomeScreenUi(
            HomeUiData(
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
            ),
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
private fun HomeAllSectionsErrorPreview() {
    TmdbTheme {
        val data = HomeUiData(
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
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
private fun HomeAllSectionsNetworkErrorPreview() {
    TmdbTheme {
        val data = HomeUiData(
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
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

private val moviesPreview = listOf(
    Movie(
        id = 1,
        title = "Movie 1",
        averageVote = 70.7,
        releaseDate = LocalDate.parse("2022-01-01"),
        posterUrl = null
    ),
    Movie(
        id = 2,
        title = "Movie 2",
        averageVote = 20.7,
        releaseDate = LocalDate.parse("2020-01-01"),
        posterUrl = null
    ),
    Movie(
        id = 3,
        title = "Movie 3",
        averageVote = 95.7,
        releaseDate = LocalDate.parse("2021-01-01"),
        posterUrl = null
    )
)

@Preview(showBackground = false, showSystemUi = false)
@Composable
private fun HomeStateSuccessPreview() {
    TmdbTheme {
        val data = HomeUiData(
            isFullReload = false,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                )
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
private fun HomeMixedStatesPreview() {
    TmdbTheme {
        val data = HomeUiData(
            isFullReload = false,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
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
                    error = MovieGroup.Error.OtherError
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
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
private fun HomeFullReloadPreview() {
    TmdbTheme {
        val data = HomeUiData(
            isFullReload = true,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = moviesPreview,
                    isLoading = false,
                    error = null
                )
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Composable
internal fun HomeScreenUi(
    data: HomeUiData,
    onEvent: (HomeUiEvent) -> Unit
) {
    Home(
        data,
        onReloadAll = { onEvent(HomeUiEvent.ReloadAll) },
        onReloadSection = { section -> onEvent(HomeUiEvent.ReloadMovieSection(section)) },
        onMovieClick = { movieId -> onEvent(HomeUiEvent.OpenMovie(movieId)) }
    )
}

@Composable
internal fun Home(
    data: HomeUiData,
    onReloadAll: () -> Unit,
    onReloadSection: (HomeMovieSectionType) -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(HomeScreenTestTags.TAG_HOME_CONTENT),
        content = {
            Box(modifier = Modifier.padding(it)) {
                HomeContent(
                    data,
                    onReloadAll = onReloadAll,
                    onReloadSection = onReloadSection,
                    onMovieClick = onMovieClick
                )
            }
        }
    )
}

@Composable
internal fun HomeContent(
    data: HomeUiData,
    onReloadAll: () -> Unit,
    onReloadSection: (HomeMovieSectionType) -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    var isRefreshing by remember { mutableStateOf(false) }
    isRefreshing = data.isFullReload

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            onReloadAll.invoke()
        }
    )

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn {
            items(data.movieGroups, key = { it.type }) { group ->
                MovieSection(
                    group,
                    onReloadSection = { onReloadSection(group.type) },
                    onMovieClick = onMovieClick
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        PullRefreshIndicator(refreshing = isRefreshing, state = pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
