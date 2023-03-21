package com.tmdb.api.model.person

import com.tmdb.api.model.util.serializer.PersonGenderSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PersonGenderSerializer::class)
enum class PersonGender {
    NOT_SPECIFIED,
    FEMALE,
    MALE,
    NON_BINARY
}