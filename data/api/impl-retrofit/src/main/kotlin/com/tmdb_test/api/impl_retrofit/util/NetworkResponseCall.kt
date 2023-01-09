package com.tmdb_test.api.impl_retrofit.util

import com.tmdb_test.api.model.util.ApiException
import com.tmdb_test.api.model.util.ApiResponse
import java.io.IOException
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response

internal class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<ApiResponse<S, E>> {

    override fun enqueue(callback: Callback<ApiResponse<S, E>>) {
        return delegate.enqueue(
            object : Callback<S> {
                override fun onResponse(call: Call<S>, response: Response<S>) {
                    val body = response.body()
                    val code = response.code()
                    val error = response.errorBody()

                    if (response.isSuccessful) {
                        val apiResponse = if (body == null) {
                            // Response is successful but the body is null
                            ApiResponse.UnknownError(ApiException.UnknownError())
                        } else {
                            ApiResponse.Success(body)
                        }
                        callback.onResponse(this@NetworkResponseCall, Response.success(apiResponse))
                    } else {
                        val errorBody = when {
                            error == null -> null
                            error.contentLength() == 0L -> null
                            else -> {
                                try {
                                    errorConverter.convert(error)
                                } catch (ex: Exception) {
                                    null
                                }
                            }
                        }
                        val apiResponse = if (errorBody == null) {
                            val exception = ApiException.UnknownError()
                            ApiResponse.UnknownError(exception)
                        } else {
                            val exception =
                                ApiException.HttpError(message = errorBody.toString(), code = code)
                            ApiResponse.UnknownError(exception)
                        }
                        callback.onResponse(this@NetworkResponseCall, Response.success(apiResponse))
                    }
                }

                override fun onFailure(call: Call<S>, throwable: Throwable) {
                    val apiResponse = throwable.mapToApiResponse<S, E>()
                    callback.onResponse(this@NetworkResponseCall, Response.success(apiResponse))
                }
            }
        )
    }

    private fun <T : Any, E : Any> Throwable.mapToApiResponse(): ApiResponse<T, E> {
        if (this is IOException) {
            return ApiResponse.NetworkError(ApiException.NetworkError(cause = this))
        }
        return when (this) {
            is HttpException -> {
                when (this.code()) {
                    500 -> ApiException.InternalServerError(cause = this)
                    400 -> ApiException.BadRequest(cause = this)
                    401 -> ApiException.Unauthorized(cause = this)
                    else -> ApiException.HttpError(cause = this, code = this.code())
                }
            }
            else -> ApiException.UnknownError(cause = this)
        }.run {  ApiResponse.UnknownError(this) }
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ApiResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support timeout")
    }
}