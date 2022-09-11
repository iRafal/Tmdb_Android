package com.tmdb_test.data.api.model.util.serializer

import com.tmdb_test.data.api.model.JsonKeys
import com.tmdb_test.data.api.model.movie.MovieStatus
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object MovieStatusSerializer : KSerializer<MovieStatus> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(JsonKeys.STATUS, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: MovieStatus) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): MovieStatus {
        return MovieStatus.findById(decoder.decodeString())
    }
}
