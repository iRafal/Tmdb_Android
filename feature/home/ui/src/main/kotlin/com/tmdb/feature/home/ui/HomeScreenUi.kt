package com.tmdb.feature.home.ui

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
import com.tmdb.feature.home.ui.HomeUiEvent.OpenMovie
import com.tmdb.feature.home.ui.HomeUiEvent.ReloadMovieSection
import com.tmdb.feature.home.ui.data.model.HomeMovieSection
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.data.model.HomeUiData.Movie
import com.tmdb.ui.core.compose.ScrollableColumn
import com.tmdb.ui.core.data.UiState
import com.tmdb.ui.core.theme.TmdbTheme
import kotlinx.datetime.LocalDate

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsLoadingPreview() {
    TmdbTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Loading(),
                NOW_POPULAR to UiState.Loading(),
                TOP_RATED to UiState.Loading(),
                UPCOMING to UiState.Loading()
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsErrorPreview() {
    TmdbTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Error(),
                NOW_POPULAR to UiState.Error(),
                TOP_RATED to UiState.Error(),
                UPCOMING to UiState.Error()
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsNetworkErrorPreview() {
    TmdbTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.NetworkError(),
                NOW_POPULAR to UiState.NetworkError(),
                TOP_RATED to UiState.NetworkError(),
                UPCOMING to UiState.NetworkError()
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeStateSuccessPreview() {
    TmdbTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = LocalDate.parse("1 Jan 2022"),
                posterUrl = null
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = LocalDate.parse("1 Jan 2020"),
                posterUrl = null
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = LocalDate.parse("1 Jan 2021"),
                posterUrl = null
            )
        )
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Success(movies),
                NOW_POPULAR to UiState.Success(movies),
                TOP_RATED to UiState.Success(movies),
                UPCOMING to UiState.Success(movies)
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeMixedStatesPreview() {
    TmdbTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = LocalDate.parse("1 Jan 2022"),
                posterUrl = null
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = LocalDate.parse("1 Jan 2020"),
                posterUrl = null
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = LocalDate.parse("1 Jan 2021"),
                posterUrl = null
            )
        )
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Success(movies),
                NOW_POPULAR to UiState.NetworkError(),
                TOP_RATED to UiState.Error(),
                UPCOMING to UiState.Loading()
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Composable
fun HomeScreenUi(
    data: HomeUiData,
    onEvent: (HomeUiEvent) -> Unit
) {
    Home(
        data,
        onReloadSection = { section -> onEvent(ReloadMovieSection(section)) },
        onMovieClick = { movieId -> onEvent(OpenMovie(movieId)) }
    )
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
                onReloadSection = { onReloadSection(section) },
                onMovieClick = onMovieClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private val HomeMovieSection.sectionUiName: String
    get() = when (this) {
        NOW_PLAYING -> "Now Playing"
        NOW_POPULAR -> "Now Popular"
        TOP_RATED -> "Top Rated"
        UPCOMING -> "Upcoming"
    }
