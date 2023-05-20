package com.tmdb.feature.movie.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.feature.movie.details.ui.data.model.MovieDetailsUiData
import com.tmdb.ui.core.theme.TmdbTheme


@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateIdlePreview() {
    TmdbTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Idle,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateLoadingPreview() {
    TmdbTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Loading,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateErrorPreview() {
    TmdbTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Error(),
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateNetworkErrorPreview() {
    TmdbTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.NetworkError(),
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateSuccessPreview() {
    TmdbTheme {
        val data = MovieDetailsUiData()
        MovieDetailsScreenUi(
            MovieDetailsUiState.Success(data),
            onEvent = { }
        )
    }
}

@Composable
fun MovieDetailsScreenUi(
    state: MovieDetailsUiState,
    onEvent: (MovieDetailsUiEvent) -> Unit
) {
    MovieDetails(state)
}

@Composable
fun MovieDetails(state: MovieDetailsUiState) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                MovieDetailsUiState.Idle, MovieDetailsUiState.Loading -> MovieDetailsLoading()
                is MovieDetailsUiState.Error -> MovieDetailsError()
                is MovieDetailsUiState.NetworkError -> MovieDetailsNetworkError()
                is MovieDetailsUiState.Success -> MovieDetailsContent(state.data)
            }
        }
    }
}

@Composable
fun MovieDetailsContent(data: MovieDetailsUiData) {

}

@Composable
fun MovieDetailsLoading() {

}

@Composable
fun MovieDetailsError() {

}

@Composable
fun MovieDetailsNetworkError() {

}