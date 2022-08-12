package com.tmdb_test.feature.home.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tmdb_test.feature.home.data.HomeUiData
import com.tmdb_test.feature.home.data.HomeUiData.Movie
import com.tmdb_test.ui.util.UiState

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionErrorPreview() {
    MovieSectionError(
        text = "Failed to load",
        buttonText = "Reload",
        onReloadSection = { }
    )
}

@Composable
fun MovieSectionError(text: String, buttonText: String, onReloadSection: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onReloadSection() }) {
            Text(text = buttonText)
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionItemPreview() {
    MovieSectionItem(
        movie = HomeUiData.Movie(
            id = 1, title = "Movie 1", averageVote = 70.7,
            releaseDate = "1 Jan 2022",
            posterUrl = null
        ),
        onMovieClick = { }
    )
}

@Composable
fun MovieSectionItem(
    movie: HomeUiData.Movie,
    onMovieClick: (movieId: Int) -> Unit
) {
    Column(
        modifier = Modifier.clickable { onMovieClick(movie.id) }
    ) {
        AsyncImage(
            model = movie.posterUrl,
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.CenterHorizontally),
            placeholder = painterResource(android.R.drawable.ic_menu_gallery),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(text = movie.title, style = MaterialTheme.typography.body2)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = movie.releaseDate, style = MaterialTheme.typography.caption)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = movie.averageVote.toString(), style = MaterialTheme.typography.caption)
        }
    }
}

private val moviesPreview = listOf(
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

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionListPreview() {
    MovieSectionList(movies = moviesPreview, onMovieClick = { })
}

@Composable
fun MovieSectionList(movies: List<HomeUiData.Movie>, onMovieClick: (movieId: Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            movies.forEach { movie ->
                item(key = movie.id) {
                    MovieSectionItem(movie, onMovieClick)
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionLoadingStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Loading(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Error(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionNetworkErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.NetworkError(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionSuccessStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Success(moviesPreview),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Composable
fun MovieSection(
    title: String,
    sectionState: UiState<List<Movie>>,
    onReloadSection: () -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    Column {
        Text(text = title, style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(16.dp))

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            when (sectionState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }
                is UiState.Error -> {
                    MovieSectionError(
                        text = "Failed to load",
                        buttonText = "Reload",
                        onReloadSection = onReloadSection
                    )
                }
                is UiState.NetworkError -> {
                    MovieSectionError(
                        text = "No internet",
                        buttonText = "Reload",
                        onReloadSection = onReloadSection
                    )
                }
                is UiState.Success<List<Movie>> -> {
                    MovieSectionList(sectionState.data, onMovieClick)
                }
            }
        }
    }
}