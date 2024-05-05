package com.tmdb.api.config.url.provider.person

import com.tmdb.data.api.config.url.provider.person.PersonUrlProvider
import com.tmdb.data.api.config.url.provider.person.PersonUrlProviderImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class PersonUrlProviderTest {
    private val baseUrl = "http://example.com/api/"
    private val personUrlProvider: PersonUrlProvider by lazy {
        PersonUrlProviderImpl(baseUrl)
    }

    @Test
    fun `personUrlProvider urls test`() {
        val personId = 12345
        val expected = setOf("${baseUrl}person/$personId}")
        val actual = setOf(personUrlProvider.personDetailsUrl(personId))

        assertEquals(expected = expected, actual = actual)
    }
}
