package com.tmdb.api.model.person

import com.tmdb.api.model.util.serializer.JsonKeys
import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
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
        "biography": "William Bradley \"Brad\" Pitt (born December 18, 1963) is an American actor and film producer.",
        "popularity": 10.647,
        "place_of_birth": "Shawnee, Oklahoma, USA",
        "profile_path": "/kU3B75TyRiCgE270EyZnHjfivoq.jpg",
        "adult": false,
        "imdb_id": "nm0000093",
        "homepage": null
    }
 */
@Serializable
data class Person(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("also_known_as") val alsoKnownAs: List<String> = listOf(),

    @Serializable(with = LocalDateIso8601Serializer::class)
    @SerialName("birthday")
    val birthday: LocalDate? = null,

    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("deathday") val deathDay: String? = null,
    @SerialName(JsonKeys.GENDER) val gender: PersonGender? = null,
    @SerialName("biography") val biography: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("place_of_birth") val placeOfBirth: String? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("adult") val isAdult: Boolean = false,
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("homepage") val homepage: String? = null
)
