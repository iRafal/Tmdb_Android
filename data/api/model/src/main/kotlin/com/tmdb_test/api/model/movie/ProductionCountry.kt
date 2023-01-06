package com.tmdb_test.api.model.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    {
        "id": 25,
        "logo_path": "/qZCc1lty5FzX30aOCVRBLzaVmcp.png",
        "name": "20th Century Fox",
        "origin_country": "US"
    }
 */
@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1") val iso_3166_1: String? = null,
    @SerialName("name") val name: String? = null,
)
