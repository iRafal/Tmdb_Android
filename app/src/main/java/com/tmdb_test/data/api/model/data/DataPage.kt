package com.tmdb_test.data.api.model.data

import com.tmdb_test.data.api.model.util.serializer.DateSerializer
import java.time.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataPage<T>(
    @SerialName("page") val page: Int? = null,
    @SerialName("results") val results: List<T> = emptyList(),
    @SerialName("dates") val dates: Dates? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null,
) {
    @Serializable
    data class Dates(
        @SerialName("maximum")
        @Serializable(with = DateSerializer::class)
        val maximum: LocalDate? = null,

        @SerialName("minimum")
        @Serializable(with = DateSerializer::class)
        val minimum: LocalDate? = null
    )
}

