package com.tmdb.data.api.implKtor.movie

import com.tmdb.api.model.util.ApiException
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProviderImpl
import com.tmdb.data.api.implKtor.di.module.ApiUtilModule
import com.tmdb.data.api.implKtor.util.ApiErrorMapper
import com.tmdb.data.api.implKtor.util.ApiErrorMapperImpl
import com.tmdb.data.api.implKtor.util.ModelUtil
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.MockRequestHandler
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class MovieApiTest {
    private val movieModel = ModelUtil.movieModel
    private val movieJson = """
        {
              "adult": false,
              "backdrop_path": "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
              "belongs_to_collection": null,
              "budget": 63000000,
              "genres": [
                {
                  "id": 18,
                  "name": "Drama"
                }
              ],
              "homepage": "",
              "id": 550,
              "imdb_id": "tt0137523",
              "original_language": "en",
              "original_title": "Fight Club",
              "overview": "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male",
              "popularity": 0.5,
              "poster_path": null,
              "production_companies": [
                {
                  "id": 508,
                  "logo_path": "/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png",
                  "name": "Regency Enterprises",
                  "origin_country": "US"
                },
                {
                  "id": 711,
                  "logo_path": null,
                  "name": "Fox 2000 Pictures",
                  "origin_country": ""
                }
              ],
              "production_countries": [
                {
                  "iso_3166_1": "US",
                  "name": "United States of America"
                }
              ],
              "release_date": "1999-10-12",
              "revenue": 100853753,
              "runtime": 139,
              "spoken_languages": [
                {
                  "iso_639_1": "en",
                  "name": "English"
                }
              ],
              "status": "Released",
              "tagline": "How much can you know about yourself if you've never been in a fight?",
              "title": "Fight Club",
              "video": false,
              "vote_average": 7.8,
              "vote_count": 3439
        }
    """.trimIndent()

    private val moviesListBody = """
    {
        "page": 1,
        "total_pages": 1,
        "total_results": 1,
        "results": [
            $movieJson
        ]
    }
    """.trimIndent()

    private val movieId = ModelUtil.movieModel.id ?: 0

    private lateinit var httpClient: HttpClient

    private val movieApi: MovieApi
        get() = MovieApiImpl(httpClient, movieUrlProvider)

    private val movieUrlProvider: MovieUrlProvider by lazy(LazyThreadSafetyMode.NONE) {
        MovieUrlProviderImpl("/")
    }

    private val apiErrorMapper: ApiErrorMapper by lazy(LazyThreadSafetyMode.NONE) {
        ApiErrorMapperImpl()
    }

    private val responseHeaders by lazy(LazyThreadSafetyMode.NONE) {
        headersOf(HttpHeaders.ContentType to listOf(ContentType.Application.Json.toString()))
    }

    private fun httpClientSetup(mockRequestHandler: MockRequestHandler) {
        httpClient = HttpClient(MockEngine) {
            engine {
                addHandler(mockRequestHandler)
            }
            install(ContentNegotiation) {
                json(ApiUtilModule.json())
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    throw apiErrorMapper.map(cause)
                }
            }
        }
    }

    @Test
    fun `movie by id success`() = runTest {
        val mockRequestHandler = { scope: MockRequestHandleScope, request: HttpRequestData ->
            when (request.url.encodedPath) {
                "/movie/$movieId" -> {
                    scope.respond(movieJson, HttpStatusCode.OK, responseHeaders)
                }
                else -> error("${HttpStatusCode.NotFound} ${request.url.encodedPath}")
            }
        }
        httpClientSetup(mockRequestHandler)

        val response = movieApi.movie(movieId)
        assertTrue(response.isSuccess)
        assertEquals(expected = (response as ApiResponse.Success).data, actual = movieModel)
    }

    @Test
    fun `movie by id no body`() = runTest {
        val mockRequestHandler = { scope: MockRequestHandleScope, request: HttpRequestData ->
            when (request.url.encodedPath) {
                "/movie/$movieId" -> {
                    scope.respond("", HttpStatusCode.OK, responseHeaders)
                }
                else -> error("${HttpStatusCode.NotFound} ${request.url.encodedPath}")
            }
        }
        httpClientSetup(mockRequestHandler)

        val response = movieApi.movie(movieId)
        assertTrue(response.isUnknownError)
        assertTrue(response is ApiResponse.UnknownError)
        assertTrue(response.cause is ApiException.UnknownError)
    }

    @Test
    fun `movie by id api error no body`() = runTest {
        val mockRequestHandler = { scope: MockRequestHandleScope, request: HttpRequestData ->
            when (request.url.encodedPath) {
                "/movie/$movieId" -> {
                    scope.respond("", HttpStatusCode.BadRequest, responseHeaders)
                }
                else -> error("${HttpStatusCode.NotFound} ${request.url.encodedPath}")
            }
        }
        httpClientSetup(mockRequestHandler)

        val response = movieApi.movie(movieId)
        assertTrue(response.isUnknownError)
        assertTrue(response is ApiResponse.UnknownError)
        assertTrue(response.cause is ApiException.UnknownError)
    }

    @AfterTest
    fun teardown() {
        httpClient.close()
    }
}
