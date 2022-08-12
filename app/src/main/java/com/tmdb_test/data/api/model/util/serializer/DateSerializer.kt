package com.tmdb_test.data.api.model.util.serializer

import java.time.LocalDate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DateSerializer : KSerializer<LocalDate?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDate?", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate?) {
        encoder.encodeString(value.toString()) //TODO
    }

    override fun deserialize(decoder: Decoder): LocalDate? {
        return LocalDate.parse(decoder.decodeString())
    }
}
