package com.tmdb_test.util

import androidx.constraintlayout.compose.DesignElements.map
import com.tmdb_test.R
import com.tmdb_test.data.api.impl_retrofit.NetworkErrorModel
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.util.ApiResponse
import com.tmdb_test.data.api.util.ApiResponse.ApiError
import com.tmdb_test.data.api.util.ApiResponse.UnknownError
import com.tmdb_test.data.util.DataState
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.FeatureState.Error
import com.tmdb_test.store.FeatureState.Loading
import com.tmdb_test.store.FeatureState.NetworkError
import com.tmdb_test.store.FeatureState.Success
import com.tmdb_test.ui.util.UiState


fun <T, R> FeatureState<T>.map(dataMapper: (T) -> R): UiState<R> {
    return when (this) {
        is Loading -> UiState.Loading()
        is Success -> {
            UiState.Success(dataMapper(this.data))
        }
        is Error -> UiState.Error()
        is NetworkError -> UiState.NetworkError()
    }
}


typealias MoviesApiResponseToDataStateMapper = (ApiResponse<DataPage<Movie>, NetworkErrorModel>) -> DataState<List<Movie>>
val moviesApiResponseToDataStateMapper: MoviesApiResponseToDataStateMapper = {
    val dataMapper: (DataPage<Movie>) -> List<Movie> = { dataPage -> dataPage.results }
    it.map(dataMapper)
}

fun <T : Any, E : NetworkErrorModel, R> ApiResponse<T, E>.map(dataMapper: (T) -> R): DataState<R> {
    return when(this) {
        is UnknownError -> DataState.Error(this.cause)
        is ApiResponse.NetworkError -> DataState.NetworkError(this.cause)
        is ApiResponse.Success<T> -> DataState.Success(dataMapper(this.data))
        is ApiError -> DataState.Error()
    }
}

typealias MoviesDataStateToFeatureStateMapper = (DataState<List<Movie>>) -> FeatureState<List<Movie>>
val moviesDataStateToFeatureStateMapper: MoviesDataStateToFeatureStateMapper = { it.map() }

fun <T> DataState<T>.map(): FeatureState<T> {
    return when(this) {
        is DataState.Error -> FeatureState.Error(this.cause)
        is DataState.NetworkError -> FeatureState.NetworkError(this.cause)
        is DataState.Success -> FeatureState.Success(this.data)
    }
}