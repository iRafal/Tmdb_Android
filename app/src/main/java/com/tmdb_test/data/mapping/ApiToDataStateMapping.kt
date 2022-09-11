package com.tmdb_test.data.mapping

import com.tmdb_test.data.api.util.ApiResponse
import com.tmdb_test.data.api.util.ApiResponse.ApiError
import com.tmdb_test.data.api.util.ApiResponse.UnknownError
import com.tmdb_test.data.api.util.NetworkErrorModel
import com.tmdb_test.data.model.DataState


internal fun <T : Any, E : NetworkErrorModel, R> ApiResponse<T, E>.mapApiToDataState(
    dataMapper: (T) -> R
): DataState<R> {
    return when (this) {
        is UnknownError -> DataState.Error(this.cause)
        is ApiResponse.NetworkError -> DataState.NetworkError(this.cause)
        is ApiResponse.Success<T> -> DataState.Success(dataMapper(this.data))
        is ApiError -> DataState.Error()
    }
}