package com.tmdb.api.config.url.provider.discover

import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProviderImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class DiscoverUrlProviderTest {
    private val baseUrl = "http://example.com/api/"
    private val discoverUrlProvider: DiscoverUrlProvider by lazy {
        DiscoverUrlProviderImpl(baseUrl)
    }

    @Test
    fun `discoverUrlProvider urls test`() {
        val expected = setOf("${baseUrl}discover/movie", "${baseUrl}discover/tv")
        val actual = setOf(discoverUrlProvider.discoverMovieUrl, discoverUrlProvider.discoverTvUrl)

        assertEquals(expected = expected, actual = actual)
    }
}
