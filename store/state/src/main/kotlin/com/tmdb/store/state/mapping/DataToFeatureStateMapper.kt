package com.tmdb.store.state.mapping

import com.tmdb.data.model.state.DataState
import com.tmdb.store.state.FeatureState

typealias DataToFeatureStateMapper<T> = (input: DataState<T>) -> FeatureState<T>

fun <T> mapDataToFeatureStateImpl(input: DataState<T>): FeatureState<T> {
    return when(input) {
        is DataState.Error -> FeatureState.Error(input.cause)
        is DataState.NetworkError -> FeatureState.NetworkError(input.cause)
        is DataState.Success -> FeatureState.Success(input.data)
    }
}