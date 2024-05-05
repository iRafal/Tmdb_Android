package com.tmdb.api.model.person

import com.tmdb.api.model.di.UnitTestServiceLocator
import com.tmdb.api.model.util.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class PersonJsonParsingTest {

    @ExperimentalSerializationApi
    private val json = UnitTestServiceLocator.json

    @Test
    fun `parse person`() {
        val personJson = """
        {
            "birthday": "1963-12-18",
            "known_for_department": "Acting",
            "deathday": null,
            "id": 287,
            "name": "Brad Pitt",
            "also_known_as": [
                "برد پیت",
                "Бред Питт",
                "Бред Пітт",
                "Buratto Pitto",
                "Брэд Питт",
                "畢·彼特",
                "ブラッド・ピット",
                "브래드 피트",
                "براد بيت",
                "แบรด พิตต์"
            ],
            "gender": 2,
            "biography": "William Bradley \"Brad\" Pitt (born December 18, 1963) is an American actor and film",
            "popularity": 10.647,
            "place_of_birth": "Shawnee, Oklahoma, USA",
            "profile_path": "/kU3B75TyRiCgE270EyZnHjfivoq.jpg",
            "adult": false,
            "imdb_id": "nm0000093",
            "homepage": null
        }
        """.trimIndent()
        val actual = json.decodeFromString<Person>(personJson)

        assertEquals(expected =  ModelUtil.personModel, actual = actual)
    }

    @Test
    fun `parse empty movie json object`() {
        val personJson = "{ }"
        val actual = json.decodeFromString<Person>(personJson)

        assertEquals(expected = Person(), actual = actual)
    }

    @Test(expected = Exception::class)
    fun `parse empty person json`() {
        val actual = json.decodeFromString<Person>("")

        assertNull(actual)
    }
}
