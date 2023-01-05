//package com.tmdb_test.data.api.impl.movie
//
//import com.tmdb_test.BuildConfig
//import com.tmdb_test.api.model.DataPage
//import com.tmdb_test.api.model.Movie
//import com.tmdb_test.data.api.util.ApiResponse
//import com.tmdb_test.data.api.util.runApiCall
//import io.ktor.client.HttpClient
//import io.ktor.client.call.body
//import io.ktor.client.request.get
//import io.ktor.client.request.parameter
//
//class MovieApiImpl(private val client: HttpClient, private val baseUrl: String) : MovieApi {
//
//    override suspend fun movie(
//        movieId: Int,
//        language: String?,
//        appendToResponse: String?,
//    ): ApiResponse<Movie> {
//        return runApiCall {
//            client.get("${baseUrl}movie/$movieId") {
//                parameter("language", language)
//                parameter("append_to_response", appendToResponse)
//            }.body()
//        }
//    }
//
//    override suspend fun latestMovie(
//        language: String?,
//    ): ApiResponse<Movie> {
//        return runApiCall {
//            client.get("${baseUrl}movie/latest") {
//                parameter("language", language)
//            }.body()
//        }
//    }
//
//    override suspend fun nowPlayingMovies(
//        language: String?,
//        page: Int?,
//        region: String?,
//    ): ApiResponse<DataPage<Movie>> {
//        return runApiCall {
//            client.get("${baseUrl}movie/now_playing") {
//                parameter("language", language)
//                parameter("page", page)
//                parameter("region", region)
//            }.body()
//        }
//    }
//
//    override suspend fun nowPopularMovies(
//        language: String?,
//        page: Int?,
//        region: String?,
//    ): ApiResponse<DataPage<Movie>> {
//        return runApiCall {
//            client.get("${baseUrl}movie/popular") {
//                parameter("language", language)
//                parameter("page", page)
//                parameter("region", region)
//            }.body()
//        }
//    }
//
//    override suspend fun topRatedMovies(
//        language: String?,
//        page: Int?,
//        region: String?,
//    ): ApiResponse<DataPage<Movie>> {
//        return runApiCall {
//            client.get("${baseUrl}movie/top_rated") {
//                parameter("language", language)
//                parameter("page", page)
//                parameter("region", region)
//            }.body()
//        }
//    }
//
//    override suspend fun upcomingMovies(
//        language: String?,
//        page: Int?,
//        region: String?,
//    ): ApiResponse<DataPage<Movie>> {
//        return runApiCall {
//            client.get("${baseUrl}movie/upcoming") {
//                parameter("language", language)
//                parameter("page", page)
//                parameter("region", region)
//            }.body()
//        }
//    }
//}