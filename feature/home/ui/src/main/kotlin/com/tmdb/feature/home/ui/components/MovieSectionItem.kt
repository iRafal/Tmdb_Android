package com.tmdb.feature.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.tmdb.feature.home.ui.HomeScreenTestTags
import com.tmdb.feature.home.ui.data.model.Movie
import com.tmdb.ui.core.compose.theme.TmdbTheme
import com.tmdb.ui.core.compose.units.Spacing
import kotlinx.datetime.LocalDate

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieSectionItemPreview() {
    TmdbTheme {
        MovieSectionItem(
            movie = Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = LocalDate.parse("2022-01-01"),
                posterUrl = null
            ),
            onMovieClick = { }
        )
    }
}

@Composable
internal fun MovieSectionItem(
    movie: Movie,
    onMovieClick: (movieId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .testTag(HomeScreenTestTags.TAG_MOVIE_ITEM)
            .clickable { onMovieClick(movie.id) }
            .width(Spacing.dp136),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = movie.posterUrl,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(Spacing.dp136)
                .clip(RoundedCornerShape(Spacing.dp16)),
            contentScale = ContentScale.Crop,
            placeholder = rememberVectorPainter(image = Filled.Image),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(Spacing.dp8))

        Text(
            text = movie.title,
            modifier = Modifier.padding(horizontal = Spacing.dp16),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.dp8))
    }
}
