package com.tmdb.api.impl_retrofit.util

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

class ApiResponseInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        return response.newBuilder().build()
    }
}