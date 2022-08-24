package com.tmdb_test.data.api.model.movie

import com.tmdb_test.util.model.ModelUtil
import com.tmdb_test.data.api.model.data.DataPage
import com.tmdb_test.data.api.model.data.DataPage.Dates
import com.tmdb_test.di.ServiceLocator
import java.time.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalSerializationApi::class)
class MovieJsonParsingTest {

    @ExperimentalSerializationApi
    private val json = ServiceLocator.json

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
    fun `parse movie`() {
        val expected = movieModel
        val actual = json.decodeFromString<Movie>(movieJson)
        assertEquals(expected, actual)
    }

    @Test
    fun `parse empty movie json object`() {
        val movieJson = "{ }"
        val expected = Movie()
        val actual = json.decodeFromString<Movie>(movieJson)
        assertEquals(expected, actual)
    }

    @Test(expected = Exception::class)
    fun `parse empty movie json`() {
        val movieJson = ""
        json.decodeFromString<Movie>(movieJson)
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
                maximum = LocalDate.of(2016, 9, 22),
                minimum = LocalDate.of(2016, 9, 1),
            ),
            totalPages = 1,
            totalResults = 1,
        )
        val actual = json.decodeFromString<DataPage<Movie>>(moviePageJson)
        assertEquals(expected, actual)
    }
}