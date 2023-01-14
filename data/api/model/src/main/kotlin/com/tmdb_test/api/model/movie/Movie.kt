package com.tmdb_test.api.model.movie

import com.tmdb_test.api.model.util.serializer.JsonKeys
import com.tmdb_test.api.model.genre.Genre
import com.tmdb_test.api.model.util.serializer.MovieStatusSerializer
import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
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
        },
        {
          "id": 20555,
          "logo_path": null,
          "name": "Taurus Film",
          "origin_country": ""
        },
        {
          "id": 54050,
          "logo_path": null,
          "name": "Linson Films",
          "origin_country": ""
        },
        {
          "id": 54051,
          "logo_path": null,
          "name": "Atman Entertainment",
          "origin_country": ""
        },
        {
          "id": 54052,
          "logo_path": null,
          "name": "Knickerbocker Films",
          "origin_country": ""
        },
        {
          "id": 25,
          "logo_path": "/qZCc1lty5FzX30aOCVRBLzaVmcp.png",
          "name": "20th Century Fox",
          "origin_country": "US"
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
 */

@Serializable
data class Movie(
    @SerialName("adult") val isAdult: Boolean = false,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("belongs_to_collection") val belongsToCollection: String? = null,
    @SerialName("budget") val budget: Double? = null,
    @SerialName("genres") val genres: List<Genre> = emptyList(),
    @SerialName("homepage") val homepage: String? = null,
    @SerialName("id") val id: Int? = null,
    /**
     * minLength: 9
     * maxLength: 9
     * pattern: ^tt[0-9]{7}
     */
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerialName("production_countries") val productionCountries: List<ProductionCountry> = emptyList(),

    @Serializable(with = LocalDateIso8601Serializer::class)
    @SerialName("release_date") val releaseDate: LocalDate? = null,

    @SerialName("revenue") val revenue: Int? = null,
    @SerialName("runtime") val runtime: Int? = null,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguage> = emptyList(),

    @Serializable(with = MovieStatusSerializer::class)
    @SerialName(JsonKeys.STATUS)
    val status: MovieStatus? = null,

    @SerialName("tagline") val tagline: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("video") val isVideo: Boolean = false,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null,
)






