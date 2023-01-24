package com.tmdb_test.ui.home

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
import com.tmdb_test.ui.home.HomeUiEvent.OpenMovie
import com.tmdb_test.ui.home.HomeUiEvent.ReloadMovieSection
import com.tmdb_test.ui.home.data.HomeMovieSection
import com.tmdb_test.ui.home.data.HomeUiData
import com.tmdb_test.ui.core.theme.Tmdb_TestTheme
import com.tmdb_test.ui.core.compose.ScrollableColumn
import com.tmdb_test.ui.core.data.UiState.Error
import com.tmdb_test.ui.core.data.UiState.Loading
import com.tmdb_test.ui.core.data.UiState.NetworkError
import com.tmdb_test.ui.core.data.UiState.Success
import com.tmdb_test.ui.home.data.HomeMovieSection.NOW_PLAYING
import com.tmdb_test.ui.home.data.HomeMovieSection.NOW_POPULAR
import com.tmdb_test.ui.home.data.HomeMovieSection.TOP_RATED
import com.tmdb_test.ui.home.data.HomeMovieSection.UPCOMING
import com.tmdb_test.ui.home.data.HomeUiData.Movie
import kotlinx.datetime.LocalDate


@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsLoadingPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to Loading(),
                NOW_POPULAR to Loading(),
                TOP_RATED to Loading(),
                UPCOMING to Loading(),
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
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to Error(),
                NOW_POPULAR to Error(),
                TOP_RATED to Error(),
                UPCOMING to Error(),
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
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to NetworkError(),
                NOW_POPULAR to NetworkError(),
                TOP_RATED to NetworkError(),
                UPCOMING to NetworkError(),
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
    Tmdb_TestTheme {
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
                NOW_PLAYING to Success(movies),
                NOW_POPULAR to Success(movies),
                TOP_RATED to Success(movies),
                UPCOMING to Success(movies),
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
    Tmdb_TestTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate =LocalDate.parse("1 Jan 2022"),
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
                NOW_PLAYING to Success(movies),
                NOW_POPULAR to NetworkError(),
                TOP_RATED to Error(),
                UPCOMING to Loading(),
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
        HomeMovieSection.NOW_PLAYING -> "Now Playing"
        HomeMovieSection.NOW_POPULAR -> "Now Popular"
        HomeMovieSection.TOP_RATED -> "Top Rated"
        HomeMovieSection.UPCOMING -> "Upcoming"
    }
