package com.tmdb_test.api.model.person

import com.tmdb_test.api.model.util.serializer.PersonGenderSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PersonGenderSerializer::class)
sealed class PersonGender(val id: Int) {
    object Gender0 : PersonGender(0) //TODO find proper name
    object Gender1 : PersonGender(1) //TODO find proper name
    object Gender2 : PersonGender(2) //TODO find proper name
    object Gender3 : PersonGender(3) //TODO find proper name

    companion object {
        fun findById(id: Int): PersonGender {
            return findByIdOrNull(id)
                ?: throw IllegalArgumentException("No ${PersonGender::class.simpleName} with id:$id")
        }

        fun findByIdOrNull(id: Int): PersonGender? {
            return when (id) {
                0 -> Gender0
                1 -> Gender1
                2 -> Gender2
                3 -> Gender3
                else -> null
            }
        }
    }
}