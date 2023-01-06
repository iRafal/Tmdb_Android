package com.tmdb_test.api.model.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    {
        "iso_3166_1": "US",
        "name": "United States of America"
    }
 */
@Serializable
data class ProductionCompany(
    @SerialName("id") val id: Int? = null,
    @SerialName("logo_path") val logoPath: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("origin_country") val originCountry: String? = null,
)
