package com.tmdb.api.model.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataPage<T>(
    @SerialName("page") val page: Int? = null,
    @SerialName("results") val results: List<T> = emptyList(),
    @SerialName("dates") val dates: Dates? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null
) {
    @Serializable
    data class Dates(
        @SerialName("maximum")
        @Serializable(with = LocalDateIso8601Serializer::class)
        val maximum: LocalDate? = null,

        @SerialName("minimum")
        @Serializable(with = LocalDateIso8601Serializer::class)
        val minimum: LocalDate? = null
    )
}
