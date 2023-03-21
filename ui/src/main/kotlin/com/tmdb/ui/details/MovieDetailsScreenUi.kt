package com.tmdb.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.ui.details.data.MovieDetailsUiData
import com.tmdb.ui.core.theme.TmdbTheme
import com.tmdb.ui.details.MovieDetailsUiState.Error
import com.tmdb.ui.details.MovieDetailsUiState.Idle
import com.tmdb.ui.details.MovieDetailsUiState.Loading
import com.tmdb.ui.details.MovieDetailsUiState.NetworkError
import com.tmdb.ui.details.MovieDetailsUiState.Success


@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateIdlePreview() {
    TmdbTheme {
        MovieDetailsScreenUi(
            Idle,
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
            Loading,
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
            Error(),
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
            NetworkError(),
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
            Success(data),
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