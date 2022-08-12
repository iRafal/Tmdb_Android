package com.tmdb_test.feature.movie.details.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb_test.feature.movie.details.content.MovieDetailsUiState.Error
import com.tmdb_test.feature.movie.details.content.MovieDetailsUiState.Idle
import com.tmdb_test.feature.movie.details.content.MovieDetailsUiState.Loading
import com.tmdb_test.feature.movie.details.content.MovieDetailsUiState.NetworkError
import com.tmdb_test.feature.movie.details.content.MovieDetailsUiState.Success
import com.tmdb_test.feature.movie.details.data.MovieDetailsUiData
import com.tmdb_test.ui.theme.Tmdb_TestTheme


@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateIdlePreview() {
    Tmdb_TestTheme {
        MovieDetailsScreen(
            Idle,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateLoadingPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreen(
            Loading,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateErrorPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreen(
            Error(),
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateNetworkErrorPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreen(
            NetworkError(),
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateSuccessPreview() {
    Tmdb_TestTheme {
        val data = MovieDetailsUiData()
        MovieDetailsScreen(
            Success(data),
            onEvent = { }
        )
    }
}

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsUiState,
    onEvent: (MovieDetailsUiEvent) -> Unit
) {
    Tmdb_TestTheme {
        MovieDetails(state)
    }
}

@Composable
fun MovieDetails(state: MovieDetailsUiState) {
    Scaffold()
    {
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                Idle, Loading -> MovieDetailsLoading()
                is Error -> MovieDetailsError()
                is NetworkError -> MovieDetailsNetworkError()
                is Success -> MovieDetailsContent(state.data)
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