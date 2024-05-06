package com.tmdb.feature.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.feature.home.ui.HomeScreenTestTags
import com.tmdb.feature.home.ui.R
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.Movie
import com.tmdb.feature.home.ui.data.model.MovieGroup
import com.tmdb.feature.home.ui.data.model.MovieGroup.Error.NetworkError
import com.tmdb.feature.home.ui.data.model.MovieGroup.Error.OtherError
import com.tmdb.ui.core.compose.ComposeTestTags
import com.tmdb.ui.core.compose.theme.TmdbTheme
import com.tmdb.ui.core.compose.units.Spacing
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

private val previewDateToday = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

private val moviesPreview = listOf(
    Movie(
        id = 1,
        title = "Movie 1",
        averageVote = 70.7,
        releaseDate = previewDateToday,
        posterUrl = null
    ),
    Movie(
        id = 2,
        title = "Movie 2",
        averageVote = 20.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 1)),
        posterUrl = null
    ),
    Movie(
        id = 3,
        title = "Movie 3",
        averageVote = 95.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 2)),
        posterUrl = null
    )
)

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieSectionLoadingStatePreview() {
    TmdbTheme {
        MovieSection(
            movieGroup = MovieGroup(
                title = R.string.now_playing,
                type = NOW_PLAYING,
                movies = emptyList(),
                isLoading = true,
                error = null,
            ),
            onReloadSection = { },
            onMovieClick = { }
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieSectionErrorStatePreview() {
    TmdbTheme {
        MovieSection(
            movieGroup = MovieGroup(
                title = R.string.now_playing,
                type = NOW_PLAYING,
                movies = emptyList(),
                isLoading = false,
                error = OtherError,
            ),
            onReloadSection = { },
            onMovieClick = { }
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieSectionNetworkErrorStatePreview() {
    TmdbTheme {
        MovieSection(
            movieGroup = MovieGroup(
                title = R.string.now_playing,
                type = NOW_PLAYING,
                movies = emptyList(),
                isLoading = false,
                error = NetworkError,
            ),
            onReloadSection = { },
            onMovieClick = { }
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieSectionSuccessStatePreview() {
    TmdbTheme {
        MovieSection(
            movieGroup = MovieGroup(
                title = R.string.now_playing,
                type = NOW_PLAYING,
                movies = moviesPreview,
                isLoading = false,
                error = null,
            ),
            onReloadSection = { },
            onMovieClick = { }
        )
    }
}

@Composable
internal fun MovieSection(
    movieGroup: MovieGroup,
    onReloadSection: () -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .testTag(HomeScreenTestTags.TAG_MOVIE_SECTION_HEADER)
                .padding(horizontal = Spacing.dp16),
            text = stringResource(id = movieGroup.title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            if (movieGroup.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.testTag(ComposeTestTags.TAG_CIRCULAR_PROGRESS_INDICATOR)
                )
                return
            }

            if (movieGroup.error == OtherError) {
                MovieSectionError(
                    buttonText = stringResource(id = R.string.reload),
                    onReloadSection = onReloadSection
                )
                return
            }

            if (movieGroup.error == NetworkError) {
                MovieSectionError(
                    buttonText = stringResource(id = R.string.reload),
                    onReloadSection = onReloadSection
                )
                return
            }

            MovieSectionList(movieGroup.movies, onMovieClick)
        }
    }
}
