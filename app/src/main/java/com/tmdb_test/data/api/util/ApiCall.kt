package com.tmdb_test.data.api.util

//TODO review & remove
//
//suspend fun <T> runApiCall(apiCall: suspend () -> T): ApiResponse<T> {
//    return try {
//        ApiResponse.Success(apiCall.invoke())
//    } catch (e: Throwable) {
//        when(e) {
//            is ApiError.NetworkError -> ApiResponse.NetworkError(e)
//            else -> ApiResponse.Error(e)
//        }
//    }
//}
