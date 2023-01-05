//package com.tmdb_test.data.api.impl.genre
//
//import com.tmdb_test.api.model.Genre
//import com.tmdb_test.api.model.GenresList
//import com.tmdb_test.data.api.util.ApiResponse
//import com.tmdb_test.data.api.util.runApiCall
//import io.ktor.client.HttpClient
//import io.ktor.client.call.body
//import io.ktor.client.request.get
//import io.ktor.client.request.parameter
//
//class GenreApiImpl(private val client: HttpClient, private val baseUrl: String) : GenreApi {
//
//    override suspend fun genreMovieList(
//        language: String?,
//    ): ApiResponse<List<Genre>> {
//        return runApiCall {
//            client.get("${baseUrl}genre/movie/list") {
//                parameter("language", language)
//            }.body<GenresList>().genres
//        }
//    }
//
//    override suspend fun genreTvList(
//        language: String?,
//    ): ApiResponse<List<Genre>> {
//        return runApiCall {
//            client.get("${baseUrl}genre/tv/list") {
//                parameter("language", language)
//            }.body<GenresList>().genres
//        }
//    }
//}
