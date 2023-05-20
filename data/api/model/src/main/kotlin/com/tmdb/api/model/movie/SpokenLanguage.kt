package com.tmdb.api.model.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    {
        "iso_639_1": "en",
        "name": "English"
    }
 */
@Serializable
data class SpokenLanguage(
    @SerialName("iso_639_1") val iso_639_1: String? = null,
    @SerialName("name") val name: String? = null
)
