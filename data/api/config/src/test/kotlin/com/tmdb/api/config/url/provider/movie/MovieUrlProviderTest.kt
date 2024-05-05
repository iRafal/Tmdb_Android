package com.tmdb.api.config.url.provider.movie

import com.tmdb.data.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProviderImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieUrlProviderTest {
    private val baseUrl = "http://example.com/api/"
    private val movieUrlProvider: MovieUrlProvider by lazy {
        MovieUrlProviderImpl(baseUrl)
    }

    @Test
    fun `movieUrlProvider urls test`() {
        val movieId = 12345
        val expected = setOf(
            "${baseUrl}movie/$movieId",
            "${baseUrl}movie/latest",
            "${baseUrl}movie/now_playing",
            "${baseUrl}movie/popular",
            "${baseUrl}movie/top_rated",
            "${baseUrl}movie/upcoming"
        )
        val actual = setOf(
            movieUrlProvider.movieUrl(movieId),
            movieUrlProvider.latestMovieUrl,
            movieUrlProvider.nowPlayingMoviesUrl,
            movieUrlProvider.nowPopularMoviesUrl,
            movieUrlProvider.topRatedMoviesUrl,
            movieUrlProvider.upcomingMoviesUrl
        )

        assertEquals(expected = expected, actual = actual)
    }
}
