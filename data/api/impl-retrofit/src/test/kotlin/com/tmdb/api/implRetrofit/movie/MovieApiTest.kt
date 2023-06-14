package com.tmdb.api.implRetrofit.movie

import com.tmdb.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.api.implRetrofit.di.module.ApiModule
import com.tmdb.api.implRetrofit.di.module.util.ApiFactoriesModule
import com.tmdb.api.implRetrofit.di.module.util.ApiHttpClientModule
import com.tmdb.api.implRetrofit.di.module.util.ApiJsonModule
import com.tmdb.api.implRetrofit.util.ModelUtil
import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.util.ApiResponse
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

/**
 * https://hanru-yeh.medium.com/unit-test-retrofit-and-mockwebserver-a3e4e81fd2a2
 */
class MovieApiTest {
    private val mockWebServer = MockWebServer()

    private val movieBaseUrlProvider = object : BaseUrlProvider {
        override val movieApiUrl: String
            get() = mockWebServer.url("/").toString()
        override val discoverApiUrl: String
            get() = TODO("Not yet implemented")
        override val genreApiUrl: String
            get() = TODO("Not yet implemented")
        override val personApiUrl: String
            get() = TODO("Not yet implemented")
        override val apiImageUrl: String
            get() = TODO("Not yet implemented")
    }

    private val movieId = ModelUtil.movieModel.id ?: 0

    private lateinit var api: MovieApi

    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setup() {
        mockWebServer.start()

        api = ApiModule.movieApi(
            baseUrlProvider = movieBaseUrlProvider,
            client = ApiHttpClientModule.okHttpClient(
                ApiHttpClientModule.loggingInterceptor(),
                ApiHttpClientModule.requestInterceptor(),
                ApiHttpClientModule.apiResponseInterceptor()
            ),
            jsonConverterFactory = ApiFactoriesModule.jsonConverterFactory(ApiJsonModule.json()),
            scalarsConverterFactory = ApiFactoriesModule.scalarsConverterFactory(),
            factory = ApiFactoriesModule.networkResponseFactory(),
        )
    }

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
              "overview": "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
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

    @Test
    fun `movie by id success`() = runTest {
        mockWebServer.dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path?.contains("movie/$movieId") == true) {
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(movieJson)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }

        val actualResponse = api.movie(movieId)
        assert(actualResponse.isSuccess)
        assertEquals((actualResponse as ApiResponse.Success).data, movieModel)
    }

    @Test
    fun `latest movie success`() = runTest {
        mockWebServer.dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path?.contains("movie/latest") == true) {
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(movieJson)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }

        val actualResponse = api.latestMovie()
        assert(actualResponse.isSuccess)
        assertEquals((actualResponse as ApiResponse.Success).data, movieModel)
    }

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

    @Test
    fun `now playing movies success`() = runTest {
        mockWebServer.dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path?.contains("movie/now_playing") == true) {
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(moviesListBody)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }

        val actualResponse = api.nowPlayingMovies()
        assert(actualResponse.isSuccess)
        assertEquals(
            (actualResponse as ApiResponse.Success).data,
            DataPage(page = 1, results = listOf(movieModel), totalPages = 1, totalResults = 1)
        )
    }

    @Test
    fun `now popular movies success`() = runTest {
        mockWebServer.dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path?.contains("movie/popular") == true) {
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(moviesListBody)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }

        val actualResponse = api.nowPopularMovies()
        assert(actualResponse.isSuccess)
        assertEquals(
            (actualResponse as ApiResponse.Success).data,
            DataPage(page = 1, results = listOf(movieModel), totalPages = 1, totalResults = 1)
        )
    }

    @Test
    fun `top rated movies success`() = runTest {
        mockWebServer.dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path?.contains("movie/top_rated") == true) {
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(moviesListBody)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }

        val actualResponse = api.topRatedMovies()
        assert(actualResponse.isSuccess)
        assertEquals(
            (actualResponse as ApiResponse.Success).data,
            DataPage(page = 1, results = listOf(movieModel), totalPages = 1, totalResults = 1)
        )
    }

    @Test
    fun `upcoming movies success`() = runTest {
        mockWebServer.dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if (request.path?.contains("movie/upcoming") == true) {
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(moviesListBody)
                } else {
                    MockResponse().setResponseCode(404)
                }
            }
        }

        val actualResponse = api.upcomingMovies()
        assert(actualResponse.isSuccess)
        assertEquals(
            (actualResponse as ApiResponse.Success).data,
            DataPage(page = 1, results = listOf(movieModel), totalPages = 1, totalResults = 1)
        )
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}