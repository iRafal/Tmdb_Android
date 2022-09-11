package com.tmdb_test.feature.home.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb_test.feature.home.data.HomeUiData
import com.tmdb_test.ui.theme.Tmdb_TestTheme
import com.tmdb_test.ui.util.UiState.Error
import com.tmdb_test.ui.util.UiState.Loading
import com.tmdb_test.ui.util.UiState.NetworkError
import com.tmdb_test.ui.util.UiState.Success
import com.tmdb_test.ui.util.compose.ScrollableColumn


@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsLoadingPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to Loading(),
                HomeMovieSection.NOW_POPULAR to Loading(),
                HomeMovieSection.TOP_RATED to Loading(),
                HomeMovieSection.UPCOMING to Loading(),
            )
        )
        HomeScreen(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsErrorPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to Error(),
                HomeMovieSection.NOW_POPULAR to Error(),
                HomeMovieSection.TOP_RATED to Error(),
                HomeMovieSection.UPCOMING to Error(),
            )
        )
        HomeScreen(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsNetworkErrorPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to NetworkError(),
                HomeMovieSection.NOW_POPULAR to NetworkError(),
                HomeMovieSection.TOP_RATED to NetworkError(),
                HomeMovieSection.UPCOMING to NetworkError(),
            )
        )
        HomeScreen(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeStateSuccessPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            HomeUiData.Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = "1 Jan 2022",
                posterUrl = null
            ),
            HomeUiData.Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = "1 Jan 2020",
                posterUrl = null
            ),
            HomeUiData.Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = "1 Jan 2021",
                posterUrl = null
            )
        )
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to Success(movies),
                HomeMovieSection.NOW_POPULAR to Success(movies),
                HomeMovieSection.TOP_RATED to Success(movies),
                HomeMovieSection.UPCOMING to Success(movies),
            )
        )
        HomeScreen(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeMixedStatesPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            HomeUiData.Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = "1 Jan 2022",
                posterUrl = null
            ),
            HomeUiData.Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = "1 Jan 2020",
                posterUrl = null
            ),
            HomeUiData.Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = "1 Jan 2021",
                posterUrl = null
            )
        )
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to Success(movies),
                HomeMovieSection.NOW_POPULAR to NetworkError(),
                HomeMovieSection.TOP_RATED to Error(),
                HomeMovieSection.UPCOMING to Loading(),
            )
        )
        HomeScreen(
            data,
            onEvent = { }
        )
    }
}

@Composable
fun HomeScreen(
    data: HomeUiData,
    onEvent: (HomeUiEvent) -> Unit
) {
    Tmdb_TestTheme {
        Home(
            data,
            onReloadSection = { section -> onEvent(HomeUiEvent.ReloadMovieSection(section)) },
            onMovieClick = { movieId -> onEvent(HomeUiEvent.OpenMovie(movieId)) }
        )
    }
}

@Composable
fun Home(
    data: HomeUiData,
    onReloadSection: (HomeMovieSection) -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    Scaffold(
        content = {
            Box(modifier = Modifier.padding(it)) {
                HomeContent(data, onReloadSection, onMovieClick)
            }
        }
    )
}

@Composable
fun HomeContent(
    data: HomeUiData,
    onReloadSection: (HomeMovieSection) -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    ScrollableColumn(
        Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        data.movieSections.forEach { (section, sectionState) ->
            MovieSection(
                title = section.sectionUiName,
                sectionState = sectionState,
                onReloadSection = { onReloadSection(HomeMovieSection.NOW_PLAYING) },
                onMovieClick = onMovieClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private val HomeMovieSection.sectionUiName: String
    get() = when (this) {
        HomeMovieSection.NOW_PLAYING -> "Now Playing"
        HomeMovieSection.NOW_POPULAR -> "Now Popular"
        HomeMovieSection.TOP_RATED -> "Top Rated"
        HomeMovieSection.UPCOMING -> "Upcoming"
    }
