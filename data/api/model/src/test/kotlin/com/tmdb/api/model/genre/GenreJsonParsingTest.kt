package com.tmdb.api.model.genre

import com.tmdb.api.model.di.UnitTestServiceLocator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class GenreJsonParsingTest {

    @ExperimentalSerializationApi
    private val json = UnitTestServiceLocator.json

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

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `parse empty genres response json object`() {
        val genresResponseJson = "{ }"
        val actual = json.decodeFromString<GenresList>(genresResponseJson)

        assertEquals(expected = GenresList(), actual = actual)
    }

    @Test(expected = Exception::class)
    fun `parse empty genres response json`() {
        val genresResponseJson = ""
        json.decodeFromString<GenresList>(genresResponseJson)
    }
}
