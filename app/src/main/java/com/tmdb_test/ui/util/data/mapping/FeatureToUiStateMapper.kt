package com.tmdb_test.ui.util.data.mapping

import com.tmdb_test.store.state.FeatureState
import com.tmdb_test.store.state.FeatureState.Error
import com.tmdb_test.store.state.FeatureState.Loading
import com.tmdb_test.store.state.FeatureState.NetworkError
import com.tmdb_test.store.state.FeatureState.Success
import com.tmdb_test.ui.util.data.UiState

typealias FeatureToUiStateMapper<T, R> = (FeatureState<T>) -> UiState<R>

fun <T, R> mapFeatureToUiState(dataMapper: (T) -> R): FeatureToUiStateMapper<T, R> {
    return { featureState ->
        when (featureState) {
            is Loading -> UiState.Loading()
            is Success -> UiState.Success(dataMapper(featureState.data))
            is Error -> UiState.Error()
            is NetworkError -> UiState.NetworkError()
        }
    }
}