package com.tmdb_test.data.api.impl_ktor.util

import com.tmdb_test.api.model.util.ApiException
import com.tmdb_test.api.model.util.ApiResponse


suspend fun <T: Any, U: Any> runApiCall(apiCall: suspend () -> T): ApiResponse<T, U> {
    return try {
        ApiResponse.Success(apiCall.invoke())
    } catch (e: Throwable) {
        when (e) {
            is ApiException.NetworkError -> ApiResponse.NetworkError(e)
            else -> ApiResponse.UnknownError(e)
        }
    }
}