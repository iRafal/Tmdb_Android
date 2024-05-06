package com.tmdb.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.feature.home.ui.data.model.Movie
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
private fun MovieSectionListPreview() {
    TmdbTheme {
        MovieSectionList(movies = moviesPreview, onMovieClick = { })
    }
}

@Composable
internal fun MovieSectionList(movies: List<Movie>, onMovieClick: (movieId: Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Spacing.dp16),
        content = {
            itemsIndexed(items = movies, key = { _, item -> item.id }) { index, movie ->
                if (index == 0) {
                    Spacer(modifier = Modifier.size(Spacing.dp16))
                }

                MovieSectionItem(movie, onMovieClick)

                if (index == movies.lastIndex) {
                    Spacer(modifier = Modifier.size(Spacing.dp16))
                }
            }
        }
    )
}
