package com.tmdb.ui.core.data.mapping

import com.tmdb.store.state.FeatureState
import com.tmdb.ui.core.data.UiState

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
