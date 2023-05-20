package com.tmdb.api.model.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    {
        "iso_3166_1": "US",
        "name": "United States of America"
    }
 */
@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1") val iso_3166_1: String? = null,
    @SerialName("name") val name: String? = null
)
