package com.tmdb_test.api.model.util.serializer

import com.tmdb_test.api.model.person.PersonGender
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PersonGenderSerializer : KSerializer<PersonGender> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(JsonKeys.GENDER, PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: PersonGender) {
        encoder.encodeInt(value.id)
    }

    override fun deserialize(decoder: Decoder): PersonGender {
        return PersonGender.findById(decoder.decodeInt())
    }
}