package com.tmdb.ui.core.data.mapping

import com.tmdb.data.model.state.DataState
import com.tmdb.ui.core.data.UiState

abstract class DataStateToUiStateMapper<T, R> {
    protected fun map(input: DataState<T>?, dataMapper: (T) -> R): UiState<R> {
        return when (input) {
            is DataState.Success -> UiState.Success(dataMapper(input.data))
            is DataState.Error -> UiState.Error()
            is DataState.NetworkError -> UiState.NetworkError()
            else -> UiState.Loading()
        }
    }
}
