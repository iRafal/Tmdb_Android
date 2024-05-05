package com.tmdb.api.config.url.provider.base

import com.tmdb.api.config.BuildConfig.API_BASE_URL
import com.tmdb.api.config.BuildConfig.API_IMAGE_URL
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class BaseUrlProviderTest {
    private val baseUrlProvider: BaseUrlProvider by lazy {
        BaseUrlProviderImpl()
    }

    @Test
    fun `baseUrlProvider urls test`() {
        val expected = setOf(
            API_BASE_URL,
            API_BASE_URL,
            API_BASE_URL,
            API_BASE_URL,
            API_IMAGE_URL
        )
        val actual = setOf(
            baseUrlProvider.discoverApiUrl,
            baseUrlProvider.genreApiUrl,
            baseUrlProvider.movieApiUrl,
            baseUrlProvider.personApiUrl,
            baseUrlProvider.apiImageUrl
        )

        assertEquals(expected = expected, actual = actual)
    }
}
