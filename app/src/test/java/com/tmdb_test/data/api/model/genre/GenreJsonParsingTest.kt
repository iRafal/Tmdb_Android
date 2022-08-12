package com.tmdb_test.data.api.model.genre

import com.tmdb_test.di.ServiceLocator
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import org.junit.Assert.assertEquals
import org.junit.Test


@OptIn(ExperimentalSerializationApi::class)
class GenreJsonParsingTest {

    @ExperimentalSerializationApi
    private val json = ServiceLocator.json

    @Test
    fun `parse genres response json`() {
        val genresResponseJson = """
        {
          "genres": [
            {
              "id": 28,
              "name": "Action"
            }
          ]
        }
        """.trimIndent()

        val expected = GenresList(listOf(Genre(id = 28, name = "Action")))
        val actual = json.decodeFromString<GenresList>(genresResponseJson)
        assertEquals(expected, actual)
    }

    @Test
    fun `parse empty genres response json object`() {
        val genresResponseJson = "{ }"
        val expected = GenresList()
        val actual = json.decodeFromString<GenresList>(genresResponseJson)
        assertEquals(expected, actual)
    }

    @Test(expected = Exception::class)
    fun `parse empty genres response json`() {
        val genresResponseJson = ""
        json.decodeFromString<GenresList>(genresResponseJson)
    }
}