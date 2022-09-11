package com.tmdb_test.feature.mapping

import com.tmdb_test.data.model.DataState
import com.tmdb_test.data.model.MovieDataModel
import com.tmdb_test.store.FeatureState

typealias MoviesDataToFeatureStateMapper = DataToFeatureStateMapper<List<MovieDataModel>>

typealias DataToFeatureStateMapper<T> = (input: DataState<T>) -> FeatureState<T>

fun <T> mapDataToFeatureStateImpl(input: DataState<T>): FeatureState<T> {
    return when(input) {
        is DataState.Error -> FeatureState.Error(input.cause)
        is DataState.NetworkError -> FeatureState.NetworkError(input.cause)
        is DataState.Success -> FeatureState.Success(input.data)
    }
}