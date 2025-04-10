package com.tmdb.api.model.movie

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.data.DataPage.Dates
import com.tmdb.api.model.di.UnitTestServiceLocator
import com.tmdb.api.model.util.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class MovieJsonParsingTest {

    @ExperimentalSerializationApi
    private val json = UnitTestServiceLocator.json

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
              "overview": "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into",
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
    fun `parse movie`() {
        val actual = json.decodeFromString<Movie>(movieJson)

        assertEquals(expected = movieModel, actual = actual)
    }

    @Test
    fun `parse empty movie json object`() {
        val actual = json.decodeFromString<Movie>("{ }")

        assertEquals(expected = Movie(), actual = actual)
    }

    @Test(expected = Exception::class)
    fun `parse empty movie json`() {
        json.decodeFromString<Movie>("")
    }

    @Test(expected = Exception::class)
    fun `parse movies page json`() {
        val moviePageJson = """
            {
                "page": 1,
                "results": [
                    $movieJson
                ],
                "dates": {
                    "maximum": "2016-09-22",
                    "minimum": "2016-09-01",
                },
                "total_pages": 1,
                "total_results": 1
            }
        """.trimIndent()
        val expected = DataPage(
            page = 1,
            results = listOf(movieModel),
            dates = Dates(
                maximum = LocalDate(2016, 9, 22),
                minimum = LocalDate(2016, 9, 1)
            ),
            totalPages = 1,
            totalResults = 1
        )
        val actual = json.decodeFromString<DataPage<Movie>>(moviePageJson)

        assertEquals(expected = expected, actual = actual)
    }
}
