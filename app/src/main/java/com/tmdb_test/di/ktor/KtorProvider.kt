//package com.tmdb_test.di.ktor
//
//import com.tmdb_test.data.api.util.ApiError
//import com.tmdb_test.data.api.util.ApiError.BadRequest
//import com.tmdb_test.data.api.util.ApiError.HttpError
//import com.tmdb_test.data.api.util.ApiError.InternalServerError
//import com.tmdb_test.data.api.util.ApiError.NetworkError
//import com.tmdb_test.data.api.util.ApiError.Unauthorized
//import com.tmdb_test.data.api.util.ApiError.UnknownError
//import com.tmdb_test.di.ServiceLocator
//import io.ktor.client.HttpClient
//import io.ktor.client.engine.android.Android
//import io.ktor.client.plugins.ClientRequestException
//import io.ktor.client.plugins.DefaultRequest
//import io.ktor.client.plugins.HttpResponseValidator
//import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
//import io.ktor.client.plugins.defaultRequest
//import io.ktor.client.plugins.logging.DEFAULT
//import io.ktor.client.plugins.logging.LogLevel
//import io.ktor.client.plugins.logging.Logger
//import io.ktor.client.plugins.logging.Logging
//import io.ktor.client.request.header
//import io.ktor.client.statement.HttpResponsePipeline
//import io.ktor.client.statement.bodyAsText
//import io.ktor.client.utils.HttpRequestCreated
//import io.ktor.client.utils.HttpRequestIsReadyForSending
//import io.ktor.http.ContentType.Application
//import io.ktor.http.HttpHeaders
//import io.ktor.http.HttpStatusCode
//import io.ktor.http.parametersOf
//import io.ktor.serialization.kotlinx.json.json
//import io.ktor.utils.io.errors.IOException
//
//fun createKtorHttpClient(
//    apiKeyValue: String,
//    logLevel: LogLevel,
//    apiErrorMapper: ApiErrorMapper,
//    logger: Logger = Logger.DEFAULT,
//    connectTimeout: Int = 10_000
//): HttpClient {
//    return HttpClient(Android) {
//        install(ContentNegotiation) {
//            json(ServiceLocator.json)
//        }
//
//        engine {
//            this.connectTimeout = connectTimeout
//        }
//
//        install(DefaultRequest) {
//            header(HttpHeaders.ContentType, Application.Json)
//        }
//
//        defaultRequest {
//            url {
//                parameters.append("api_key", apiKeyValue)
//            }
//        }
//
//        install(Logging) {
//            this.logger = logger
//            level = logLevel
//        }
//
//        HttpResponseValidator {
//            handleResponseExceptionWithRequest { cause, request ->
//                throw apiErrorMapper(cause)
//            }
//        }
//    }
//}
//
//typealias ApiErrorMapper = suspend (cause: Throwable) -> ApiError
//
//val apiErrorMapper: ApiErrorMapper = { cause ->
//    when (cause) {
//        is IOException -> NetworkError(cause)
//        is ClientRequestException -> {
//            val clientException: ClientRequestException = cause
//            when (clientException.response.status) {
//                HttpStatusCode.InternalServerError -> {
//                    InternalServerError(clientException, clientException.response.bodyAsText())
//                }
//                HttpStatusCode.BadRequest -> {
//                    BadRequest(clientException, clientException.response.bodyAsText())
//                }
//                HttpStatusCode.Unauthorized -> {
//                    Unauthorized(clientException, clientException.response.bodyAsText())
//                }
//                else -> HttpError(clientException, clientException.response.bodyAsText())
//            }
//        }
//        else -> UnknownError(cause)
//    }
//}