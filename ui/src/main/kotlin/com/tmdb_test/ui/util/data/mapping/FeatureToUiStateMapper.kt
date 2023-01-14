package com.tmdb_test.ui.util.data.mapping

import com.tmdb_test.store.state.FeatureState
import com.tmdb_test.ui.util.data.UiState

typealias FeatureToUiStateMapper<T, R> = (FeatureState<T>) -> UiState<R>

fun <T, R> mapFeatureToUiState(dataMapper: (T) -> R): FeatureToUiStateMapper<T, R> {
    return { featureState ->
        when (featureState) {
            is FeatureState.Loading -> UiState.Loading()
            is FeatureState.Success -> UiState.Success(dataMapper(featureState.data))
            is FeatureState.Error -> UiState.Error()
            is FeatureState.NetworkError -> UiState.NetworkError()
        }
    }
}