package com.tmdb.data.source.remote.implKtor.mapping

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.model.state.DataState

internal fun <In : Any, E : NetworkErrorModel, Out> ApiResponse<In, E>.mapApiStateToDataState(
    dataMapper: (In) -> Out
): DataState<Out> = when (this) {
    is ApiResponse.UnknownError -> DataState.Error(this.cause)
    is ApiResponse.NetworkError -> DataState.NetworkError(this.cause)
    is ApiResponse.Success<In> -> DataState.Success(dataMapper(this.data))
    is ApiResponse.ApiError -> DataState.Error(Throwable("Body: [${this.body}], code=${this.code}"))
}

internal fun <In : Any, Out> apiModelToDataStateMapperImpl(
    apiModelToDataModelMapper: (In) -> Out
) = { input: ApiResponse<In, NetworkErrorModel> ->
    input.mapApiStateToDataState { apiModelToDataModelMapper(it) }
}

internal fun <In : Any, Out> apiModelListToDataStateMapperImpl(
    apiModelToDataModelMapper: (In) -> Out
) = { input: ApiResponse<DataPage<In>, NetworkErrorModel> ->
    val dataMapper: (DataPage<In>) -> List<Out> = { dataPage ->
        dataPage.results.map { apiModelToDataModelMapper(it) }
    }
    input.mapApiStateToDataState(dataMapper)
}
