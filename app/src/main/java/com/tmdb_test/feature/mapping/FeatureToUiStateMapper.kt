package com.tmdb_test.feature.mapping

import com.tmdb_test.data.model.MovieDataModel
import com.tmdb_test.feature.home.data.HomeUiData
import com.tmdb_test.store.FeatureState
import com.tmdb_test.store.FeatureState.Error
import com.tmdb_test.store.FeatureState.Loading
import com.tmdb_test.store.FeatureState.NetworkError
import com.tmdb_test.store.FeatureState.Success
import com.tmdb_test.ui.util.UiState

typealias HomeFeatureStateToUiSectionStateMapper =
        FeatureToUiStateMapper<List<MovieDataModel>, List<HomeUiData.Movie>>

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