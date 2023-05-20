package com.tmdb.api.model.util.serializer

import com.tmdb.api.model.person.PersonGender
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PersonGenderSerializer : KSerializer<PersonGender?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(JsonKeys.GENDER, PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: PersonGender?) {
        when (value) {
            PersonGender.NOT_SPECIFIED -> GENDER_NOT_SPECIFIED
            PersonGender.FEMALE -> GENDER_FEMALE
            PersonGender.MALE -> GENDER_MALE
            PersonGender.NON_BINARY -> GENDER_NON_BINARY
            null -> null
        }?.let { gender -> encoder.encodeInt(gender) }
    }

    override fun deserialize(decoder: Decoder): PersonGender? =
        when (decoder.decodeInt()) {
            GENDER_NOT_SPECIFIED -> PersonGender.NOT_SPECIFIED
            GENDER_FEMALE -> PersonGender.FEMALE
            GENDER_MALE -> PersonGender.MALE
            GENDER_NON_BINARY -> PersonGender.NON_BINARY
            else -> null
        }

    private const val GENDER_NOT_SPECIFIED = 0
    private const val GENDER_FEMALE = 1
    private const val GENDER_MALE = 2
    private const val GENDER_NON_BINARY = 3
}
