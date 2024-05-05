package com.tmdb.api.config.url.provider.genre

import com.tmdb.data.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProviderImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class GenreUrlProviderTest {
    private val baseUrl = "http://example.com/api/"
    private val genreUrlProvider: GenreUrlProvider by lazy {
        GenreUrlProviderImpl(baseUrl)
    }

    @Test
    fun `genreUrlProvider urls test`() {
        val expected = setOf("${baseUrl}genre/movie/list", "${baseUrl}genre/tv/list")
        val actual = setOf(genreUrlProvider.genreMovieListUrl, genreUrlProvider.genreTvListUrl)

        assertEquals(expected = expected, actual = actual)
    }
}
