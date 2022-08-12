package com.tmdb_test.data.api.util

import java.io.IOException
import retrofit2.HttpException
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


//suspend fun <T> runRetrofitApiCall(apiCall: suspend () -> T): ApiResponse<T> {
//    return try {
//        ApiResponse.Success(apiCall.invoke())
//    } catch (e: Throwable) {
//        when(e.mapToApiException()) {
//            is ApiError.NetworkError -> ApiResponse.NetworkError(e)
//            else -> ApiResponse.Error(e)
//        }
//    }
//}
//
//fun Throwable.mapToApiException(): ApiError {
//    return when (this) {
//        is IOException -> ApiError.NetworkError(cause = this)
//        is HttpException -> {
//            when (this.code()) {
//                500 -> ApiError.InternalServerError(cause = this)
//                400 -> ApiError.BadRequest(cause = this)
//                401 -> ApiError.Unauthorized(cause = this)
//                else -> ApiError.HttpError(cause = this, code = this.code())
//            }
//        }
//        else -> ApiError.UnknownError(cause = this)
//    }
//}
